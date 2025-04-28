package com.quyennv.lms.repository.impl;

import com.quyennv.lms.constant.enums.CourseLevel;
import com.quyennv.lms.dto.course.CreateCourseRequest;
import com.quyennv.lms.dto.course.UpdateCourseRequest;
import com.quyennv.lms.entities.Course;
import com.quyennv.lms.entities.CourseInfo;
import com.quyennv.lms.entities.Section;
import com.quyennv.lms.mappers.CourseInfoMapper;
import com.quyennv.lms.mappers.CourseMapper;
import com.quyennv.lms.repository.CourseRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CourseRepositoryImpl implements CourseRepository {

    private final CourseMapper courseMapper;

    private final CourseInfoMapper courseInfoMapper;

    private final SectionRepositoryImpl sectionRepository;

    public CourseRepositoryImpl(CourseMapper courseMapper,
                                CourseInfoMapper courseInfoMapper,
                                SectionRepositoryImpl sectionRepository) {
        this.courseMapper = courseMapper;
        this.courseInfoMapper = courseInfoMapper;
        this.sectionRepository = sectionRepository;
    }

    @Override
    public int persist(Course course) {
        course.setId(UUID.randomUUID());
        courseMapper.insert(course);
        return 1;

    }

    @Override
    public void updateCourse(Course updatedCourse, UpdateCourseRequest request) {
        updatedCourse.setUpdatedAt(new Date());
        courseMapper.updateByPrimaryKey(updatedCourse);
    }


    @Override
    public void updateCourseSelective(Course updatedCourse, UpdateCourseRequest request) {
        updatedCourse.setUpdatedAt(new Date());
        courseMapper.updateByPrimaryKeySelective(updatedCourse);
    }

    @Override
    public List<Course> findByTeacherId(UUID id) {
        return courseMapper.selectByTeacherId(id);
    }

    @Override
    public List<Course> getRegisteredCourses(UUID userId) {
        return courseMapper.selectRegisteredCourses(userId);
    }

    @Override
    public Course findByLessonId(UUID lessonId) {
        return courseMapper.selectByLessonId(lessonId);
    }

    @Override
    public Optional<Course> findById(UUID id) {
        return Optional.ofNullable(courseMapper.selectByPrimaryKey(id));
    }

    @Override
    public Optional<Course> findByCode(String code) {
        return Optional.ofNullable(courseMapper.selectByCode(code));
    }

    @Override
    public List<Course> getWithFilters(String keyword, CourseLevel level, Integer grade, String code, List<String> teacherId) {
        return null;
    }

}
