package com.quyennv.lms.service.impl;

import com.quyennv.lms.dto.course.CreateLessonRequest;
import com.quyennv.lms.dto.mappers.LessonDtoMapper;
import com.quyennv.lms.entities.Lesson;
import com.quyennv.lms.mappers.LessonMapper;
import com.quyennv.lms.service.AssignmentService;
import com.quyennv.lms.service.CourseLessonService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CourseLessonServiceImpl implements CourseLessonService {

    private final LessonMapper lessonMapper;

    private final LessonDtoMapper lessonDtoMapper;

    private final AssignmentService assignmentService;

    public CourseLessonServiceImpl(LessonMapper lessonMapper, LessonDtoMapper lessonDtoMapper, AssignmentService assignmentService) {
        this.lessonMapper = lessonMapper;
        this.lessonDtoMapper = lessonDtoMapper;
        this.assignmentService = assignmentService;
    }

    @Override
    public void updateLessons(List<Lesson> lessons, UUID sectionId) {
        if (CollectionUtils.isEmpty(lessons)) {
            return;
        }

        List<Lesson> newLessons = lessons.stream().filter(lesson -> lesson.getId() == null).toList();
        List<Lesson> oldLessonsInDb = lessonMapper.selectBySectionId(sectionId);
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
                    lessonMapper.updateByPrimaryKeySelective(lesson);
                }
            });
        }

        if (!CollectionUtils.isEmpty(newLessons)) {
            newLessons.forEach(lesson -> {
                lesson.setId(UUID.randomUUID());
                lesson.setSectionId(sectionId);
            });
            lessonMapper.insertBatch(newLessons);
        }

        if (!CollectionUtils.isEmpty(oldLessonsToDelete)) {
            oldLessonsToDelete.forEach(lesson -> {
                assignmentService.softDeleteAssignmentInLesson(lesson.getId());
                lessonMapper.softDeleteByPrimaryKey(lesson.getId());
            });
        }
    }

    @Override
    public Lesson create(CreateLessonRequest request) {
        // TODO: check course and section permission

        Lesson data = lessonDtoMapper.toLesson(request);
        data.setId(UUID.randomUUID());
        lessonMapper.insert(data);
        return data;
    }
}
