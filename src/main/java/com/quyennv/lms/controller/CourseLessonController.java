package com.quyennv.lms.controller;

import com.quyennv.lms.annotations.CurrentUser;
import com.quyennv.lms.annotations.LogsActivityAnnotation;
import com.quyennv.lms.dto.BaseResponse;
import com.quyennv.lms.dto.course.CreateLessonRequest;
import com.quyennv.lms.dto.course.DeleteLessonRequest;
import com.quyennv.lms.dto.course.UpdateLessonRequest;
import com.quyennv.lms.security.UserPrincipal;
import com.quyennv.lms.service.BaseService;
import com.quyennv.lms.service.CourseLessonService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/courses/{courseId}/sections/{sectionId}/lessons")
public class CourseLessonController {

    private final BaseService baseService;

    private final CourseLessonService lessonService;

    public CourseLessonController(BaseService baseService, CourseLessonService lessonService) {
        this.baseService = baseService;
        this.lessonService = lessonService;
    }

    @PostMapping
    @LogsActivityAnnotation
    public BaseResponse<?> createLesson(
            @PathVariable String courseId,
            @PathVariable String sectionId,
            @Valid @RequestBody CreateLessonRequest request,
            @CurrentUser UserPrincipal requester) {
        request.setCourseId(courseId);
        request.setSectionId(sectionId);
        return baseService.ofSucceeded(lessonService.create(request, requester, false));
    }

    @PutMapping("/{lessonId}")
    @LogsActivityAnnotation
    public BaseResponse<?> updateLesson(
            @PathVariable UUID courseId,
            @PathVariable UUID sectionId,
            @PathVariable UUID lessonId,
            @Valid @RequestBody UpdateLessonRequest request,
            @CurrentUser UserPrincipal requester) {
        request.setCourseId(courseId);
        request.setSectionId(sectionId);
        request.setId(lessonId);
        return baseService.ofSucceeded(lessonService.update(request, requester));
    }

    @DeleteMapping("/{lessonId}")
    @LogsActivityAnnotation
    public BaseResponse<?> deleteLesson(
            @PathVariable UUID courseId,
            @PathVariable UUID sectionId,
            @PathVariable UUID lessonId,
            @CurrentUser UserPrincipal requester) {
        DeleteLessonRequest request = new DeleteLessonRequest();
        request.setCourseId(courseId);
        request.setSectionId(sectionId);
        request.setLessonId(lessonId);
        request.setRequesterId(requester.getId());
        lessonService.delete(request);
        return baseService.ofSucceeded(null);
    }

}
