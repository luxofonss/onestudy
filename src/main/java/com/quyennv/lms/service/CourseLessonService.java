package com.quyennv.lms.service;

import com.quyennv.lms.dto.course.CreateLessonRequest;
import com.quyennv.lms.entities.Lesson;

import java.util.List;
import java.util.UUID;

public interface CourseLessonService {

    void updateLessons(List<Lesson> lessons, UUID sectionId);

    Lesson create(CreateLessonRequest request);
}
