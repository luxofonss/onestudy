package com.quyennv.lms.controller;

import com.quyennv.lms.annotations.LogsActivityAnnotation;
import com.quyennv.lms.dto.BaseResponse;
import com.quyennv.lms.dto.course.CreateLessonRequest;
import com.quyennv.lms.service.BaseService;
import com.quyennv.lms.service.CourseLessonService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
            @Valid @RequestBody CreateLessonRequest request) {
        request.setCourseId(courseId);
        request.setSectionId(sectionId);
        return baseService.ofSucceeded(lessonService.create(request));
    }

}
