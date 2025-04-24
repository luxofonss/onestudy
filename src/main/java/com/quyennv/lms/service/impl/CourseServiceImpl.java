package com.quyennv.lms.service.impl;

import com.quyennv.lms.constant.Constant;
import com.quyennv.lms.constant.enums.EnrollStatus;
import com.quyennv.lms.dto.course.*;
import com.quyennv.lms.dto.mappers.CourseDtoMapper;
import com.quyennv.lms.entities.*;
import com.quyennv.lms.exception.BusinessException;
import com.quyennv.lms.repository.*;
import com.quyennv.lms.security.UserPrincipal;
import com.quyennv.lms.service.CourseInfoService;
import com.quyennv.lms.service.CourseSectionService;
import com.quyennv.lms.service.CourseService;
import com.quyennv.lms.utils.FunctionHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final CourseDtoMapper courseDtoMapper;

    private final CourseRepository courseRepository;

    private final CourseSectionService sectionService;

    private final LessonRepository lessonRepository;

    private final UserEnrollCourseRepository userEnrollCourseRepository;

    private final UserRepository userRepository;

    private final CourseInfoService courseInfoService;

    public CourseServiceImpl(CourseDtoMapper courseDtoMapper,
                             CourseRepository courseRepository,
                             CourseSectionService sectionService,
                             LessonRepository lessonRepository,
                             UserEnrollCourseRepository userEnrollCourseRepository,
                             UserRepository userRepository,
                             CourseInfoService courseInfoService) {
        this.courseDtoMapper = courseDtoMapper;
        this.courseRepository = courseRepository;
        this.sectionService = sectionService;
        this.lessonRepository = lessonRepository;
        this.userEnrollCourseRepository = userEnrollCourseRepository;
        this.userRepository = userRepository;
        this.courseInfoService = courseInfoService;
    }

    @Override
    public CreateCourseResponse create(CreateCourseRequest request, UserPrincipal requester) {

        Course course = courseDtoMapper.toCourse(request);
        course.setTeacherId(requester.getId());
        course.setCode(FunctionHelper.generateRandomString(Constant.COURSE_CODE_LENGTH));

        try {
            courseRepository.persist(course);
        } catch (Exception e) {
            log.error("Error while creating course:: {}", e.getMessage());
            throw new RuntimeException("Error while creating course");
        }

        return courseDtoMapper.toCreateCourseResponse(course);
    }

    @Override
    public CreateCourseResponse update(String id, UpdateCourseRequest request, UserPrincipal requester) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));

        if (!requester.getId().equals(course.getTeacherId())) {
            throw new RuntimeException("You are not allowed to update this course");
        }

        Course updatedCourse = courseDtoMapper.updateRequestToCourse(request);
        updatedCourse.setId(course.getId());
        log.info("Update course: {}", updatedCourse);

        courseRepository.updateCourse(updatedCourse, request);

        if (!CollectionUtils.isEmpty(request.getSections())) {
            sectionService.updateListByCourseId(updatedCourse.getId(), updatedCourse.getSections());
        }

        if (!CollectionUtils.isEmpty(updatedCourse.getCourseInfos())) {
            courseInfoService.updateListByCourseId(updatedCourse.getId(), updatedCourse.getCourseInfos());
        }

        return null;
    }


    @Override
    public Course getCourseBasic(String id, UserPrincipal requester) {
        return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public Course getCourseDetail(String id, UserPrincipal requester) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));

        List<Section> sections = sectionService.getByCourseId(UUID.fromString(id));

        if (!CollectionUtils.isEmpty(sections)) {
            course.setSections(sections);
            sections.forEach(section -> {
                section.setLessons(lessonRepository.findLessonsBySectionId(section.getId()));
            });
        }

        List<CourseInfo> courseInfos = courseInfoService.getByCourseId(id);
        course.setCourseInfos(courseInfos);

        return course;
    }

    @Override
    public List<Course> getPublicCourses(UserPrincipal requester) {
        return null;
    }

    @Override
    public List<Course> getAllMyCourses(UserPrincipal requester) {
        return courseRepository.findByTeacherId(requester.getId());
    }

    @Override
    public UserEnrollCourse registerCourse(RegisterCourseRequest request, UserPrincipal requester) {
        Course course = courseRepository.findByCode(request.getCode())
                .orElseThrow(() -> new RuntimeException("Course not found"));
        UserEnrollCourse existRegistration = userEnrollCourseRepository
                .findByCourseIdAndUserId(UUID.fromString(course.getId().toString()), requester.getId())
                .orElse(null);

        if (Objects.nonNull(existRegistration) && existRegistration.getDeletedAt() != null) {
            existRegistration.setEnrollStatus(EnrollStatus.PENDING.toString());
            userEnrollCourseRepository.updateByPrimaryKey(existRegistration);
            return existRegistration;
        }

        UserEnrollCourse registration = new UserEnrollCourse();
        registration.setCourseId(course.getId());
        registration.setUserId(requester.getId());
        registration.setEnrollStatus(EnrollStatus.PENDING.toString());

        if (userEnrollCourseRepository.save(registration) == 1) {
            return registration;
        } else {
            throw new RuntimeException("Error while registering course");
        }
    }

    @Override
    public void addStudents(String courseId, AddStudentToCourseRequest request, UserPrincipal requester) {
        checkCourseAdminPermission(courseId, requester);

        List<User> students = userRepository.getByUserIdList(request.getStudentIds());

        if (CollectionUtils.isEmpty(students)) {
            throw new RuntimeException("No student found");
        }

        if (students.size() != request.getStudentIds().size()) {
            throw new RuntimeException("Some students not found");
        }

        List<UserEnrollCourse> existedStudents = userEnrollCourseRepository.getByStudentIds(students.stream().map(User::getId).collect(Collectors.toList()));
        List<UUID> existedStudentIds = existedStudents.stream().map(UserEnrollCourse::getUserId).toList();
        students.removeIf(student -> existedStudentIds.contains(student.getId()));

        if (CollectionUtils.isEmpty(students)) {
            throw new BusinessException(400, "All students already registered");
        }

        List<UserEnrollCourse> registrations = new ArrayList<>();
        students.forEach(student -> {
            UserEnrollCourse registration = new UserEnrollCourse();
            registration.setId(UUID.randomUUID());
            registration.setCourseId(UUID.fromString(courseId));
            registration.setUserId(student.getId());
            registration.setEnrollStatus(EnrollStatus.PENDING.toString());
            registrations.add(registration);
        });

        userEnrollCourseRepository.saveBatch(registrations);
    }

    private void checkCourseAdminPermission(String courseId, UserPrincipal requester) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

        if (!course.getTeacherId().equals(requester.getId())) {
            throw new RuntimeException("You are not allowed to add students to this course");
        }
    }

    @Override
    public UserEnrollCourse updateStudentStatus(String courseId, String studentId, UpdateCourseStudent request, UserPrincipal requester) {
        checkCourseAdminPermission(courseId, requester);

        UserEnrollCourse registration = userEnrollCourseRepository
                .findByCourseIdAndUserId(UUID.fromString(courseId), UUID.fromString(studentId))
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (registration.getDeletedAt() != null) {
            throw new RuntimeException("Student not found");
        }

        if (registration.getEnrollStatus().equals(request.getStatus())) {
            return registration;
        }

        registration.setEnrollStatus(request.getStatus());
        userEnrollCourseRepository.updateByPrimaryKey(registration);

        return registration;
    }

    @Override
    public UserEnrollCourse removeStudent(String courseId, String studentId, UserPrincipal requester) {
        checkCourseAdminPermission(courseId, requester);

        UserEnrollCourse registration = userEnrollCourseRepository
                .findByCourseIdAndUserId(UUID.fromString(courseId), UUID.fromString(studentId))
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (registration.getDeletedAt() != null) {
            throw new RuntimeException("Student not found");
        }

        registration.setDeletedAt(new Date());
        userEnrollCourseRepository.updateByPrimaryKey(registration);

        return registration;
    }

    @Override
    public List<UserEnrollCourse> getStudents(String courseId, UserPrincipal requester) {
        checkCourseAdminPermission(courseId, requester);

        List<UserEnrollCourse> students = userEnrollCourseRepository.findByCourseId(UUID.fromString(courseId));
        students.forEach(student -> {
            student.setStudent(userRepository.getUserById(student.getUserId().toString()).orElse(null));
        });

        return students;

    }

    @Override
    public List<Course> getMyRegisteredCourses(UserPrincipal requester) {
        return courseRepository.getRegisteredCourses(requester.getId());
    }

}
