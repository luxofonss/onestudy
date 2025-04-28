package com.quyennv.lms.service;

import com.quyennv.lms.dto.course.CreateLessonRequest;
import com.quyennv.lms.dto.course.DeleteLessonRequest;
import com.quyennv.lms.dto.course.UpdateLessonRequest;
import com.quyennv.lms.entities.Lesson;
import com.quyennv.lms.security.UserPrincipal;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface CourseLessonService {

    void updateLessons(List<Lesson> lessons, UUID sectionId);

    Lesson create(CreateLessonRequest request, UserPrincipal requester, boolean bypassPermission);

    Lesson update(@Valid UpdateLessonRequest request, UserPrincipal requester);

    void delete(DeleteLessonRequest request);
}
