package com.quyennv.lms.service.impl;

import com.quyennv.lms.constant.Constant;
import com.quyennv.lms.constant.enums.AssignmentType;
import com.quyennv.lms.constant.enums.LessonType;
import com.quyennv.lms.dto.assignment.CreateAssignmentRequest;
import com.quyennv.lms.dto.assignment.CreateAssignmentResponse;
import com.quyennv.lms.dto.course.CreateLessonRequest;
import com.quyennv.lms.dto.course.DeleteLessonRequest;
import com.quyennv.lms.dto.course.UpdateLessonRequest;
import com.quyennv.lms.dto.mappers.LessonDtoMapper;
import com.quyennv.lms.entities.Assignment;
import com.quyennv.lms.entities.Lesson;
import com.quyennv.lms.repository.LessonRepository;
import com.quyennv.lms.security.UserPrincipal;
import com.quyennv.lms.service.AssignmentService;
import com.quyennv.lms.service.CourseLessonService;
import com.quyennv.lms.thirdparty.storage.StorageFactory;
import com.quyennv.lms.thirdparty.storage.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class CourseLessonServiceImpl implements CourseLessonService {

    private final LessonDtoMapper lessonDtoMapper;

    private final AssignmentService assignmentService;

    private final LessonRepository lessonRepository;

    public CourseLessonServiceImpl(LessonDtoMapper lessonDtoMapper,
                                   AssignmentService assignmentService,
                                   LessonRepository lessonRepository) {
        this.lessonDtoMapper = lessonDtoMapper;
        this.assignmentService = assignmentService;
        this.lessonRepository = lessonRepository;
    }

    @Override
    public void updateLessons(List<Lesson> lessons, UUID sectionId) {
        if (CollectionUtils.isEmpty(lessons)) {
            return;
        }

        List<Lesson> newLessons = lessons.stream().filter(lesson -> lesson.getId() == null).toList();
        List<Lesson> oldLessonsInDb = lessonRepository.findBySectionId(sectionId);
        List<Lesson> oldLessonsToDelete = new ArrayList<>();

        for (Lesson l : oldLessonsInDb) {
            if (lessons.stream().noneMatch(lesson -> lesson.getId() != null && lesson.getId().equals(l.getId()))) {
                oldLessonsToDelete.add(l);
            }
        }

        if (!CollectionUtils.isEmpty(oldLessonsInDb)) {
            lessons.stream().filter(lesson -> lesson.getId() != null).forEach(lesson -> {
                Lesson oldLesson = oldLessonsInDb.stream().filter(old -> old.getId().equals(lesson.getId())).findFirst().orElse(null);
                if (oldLesson != null) {
                    lesson.setSectionId(sectionId);
                    lesson.setId(oldLesson.getId());
                    lessonRepository.updateByPrimaryKeySelective(lesson);
                }
            });
        }

        if (!CollectionUtils.isEmpty(newLessons)) {
            newLessons.forEach(lesson -> {
                lesson.setId(UUID.randomUUID());
                lesson.setSectionId(sectionId);
            });
            lessonRepository.insertBatch(newLessons);
        }

        if (!CollectionUtils.isEmpty(oldLessonsToDelete)) {
            oldLessonsToDelete.forEach(lesson -> {
                assignmentService.softDeleteAssignmentInLesson(lesson.getId());
                lessonRepository.softDeleteByPrimaryKey(lesson.getId());
            });
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Lesson create(CreateLessonRequest request, UserPrincipal requester, boolean bypassPermission) {
        // TODO: check course and section permission

        Lesson data = lessonDtoMapper.toLesson(request);
        data.setVideoUrl(request.getVideoUrl());
        lessonRepository.insert(data);

        if (Objects.equals(request.getType(), LessonType.ASSIGNMENT.name())) {
            CreateAssignmentRequest assignment = new CreateAssignmentRequest();
            assignment.setTitle(request.getName());
            assignment.setDescription(request.getDescription());
            assignment.setLessonId(data.getId().toString());
            assignment.setSectionId(request.getSectionId());
            assignment.setType(AssignmentType.REGULAR.name());
            CreateAssignmentResponse res = assignmentService.create(assignment, requester);
            data.setAssignmentId(UUID.fromString(res.getId()));
            lessonRepository.updateByPrimaryKeySelective(data);
        }
        return data;
    }

    @Override
    public Lesson update(UpdateLessonRequest request, UserPrincipal requester) {
        // TODO: check course and section permission

        Lesson existedLesson = lessonRepository.findByPrimaryKey(request.getId());

        if (existedLesson == null) {
            throw new IllegalArgumentException("Lesson not found");
        }

        Lesson data = lessonDtoMapper.toLessonUpdate(request);
        lessonRepository.updateByPrimaryKeySelective(data);
        return data;
    }

    @Override
    public void delete(DeleteLessonRequest request) {
        // check permission
        Lesson lesson = lessonRepository.findByPrimaryKey(request.getLessonId());
        if (lesson == null) {
            throw new IllegalArgumentException("Lesson not found");
        }
        lessonRepository.softDeleteByPrimaryKey(request.getLessonId());
    }
}
