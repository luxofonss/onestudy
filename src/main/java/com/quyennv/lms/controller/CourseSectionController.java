package com.quyennv.lms.controller;

import com.quyennv.lms.annotations.CurrentUser;
import com.quyennv.lms.dto.BaseResponse;
import com.quyennv.lms.dto.BasicCreationResponse;
import com.quyennv.lms.dto.course.*;
import com.quyennv.lms.security.UserPrincipal;
import com.quyennv.lms.service.BaseService;
import com.quyennv.lms.service.CourseSectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/courses/{courseId}/sections")
@Slf4j
public class CourseSectionController {

    private final BaseService baseService;

    private final CourseSectionService courseSectionService;

    public CourseSectionController(BaseService baseService, CourseSectionService courseSectionService) {
        this.baseService = baseService;
        this.courseSectionService = courseSectionService;
    }

    @PostMapping
    public BaseResponse<BasicCreationResponse> create(
            @CurrentUser UserPrincipal requester,
            @PathVariable String courseId,
            @RequestBody @Valid CourseSectionCreateRequest request) {
        request.setCourseId(UUID.fromString(courseId));
        return baseService.ofSucceeded(courseSectionService.create(request, requester));
    }

    @PutMapping("/{sectionId}")
    public BaseResponse<BasicCreationResponse> update(
            @CurrentUser UserPrincipal requester,
            @PathVariable String courseId,
            @PathVariable String sectionId,
            @RequestBody @Valid CourseSectionUpdateRequest request) {
        request.setCourseId(UUID.fromString(courseId));
        request.setId(UUID.fromString(sectionId));
        return baseService.ofSucceeded(courseSectionService.update(request, requester));
    }


    @DeleteMapping("/{sectionId}")
    public BaseResponse<BasicCreationResponse> delete(
            @CurrentUser UserPrincipal requester,
            @PathVariable String courseId,
            @PathVariable String sectionId,
            @RequestBody @Valid CourseSectionUpdateRequest request) {
        request.setCourseId(UUID.fromString(courseId));
        request.setId(UUID.fromString(sectionId));

        courseSectionService.delete(request, requester);
        return baseService.ofSucceeded(null);
    }

}
