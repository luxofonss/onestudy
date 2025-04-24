package com.quyennv.lms.controller;

import com.quyennv.lms.annotations.CurrentUser;
import com.quyennv.lms.annotations.LogsActivityAnnotation;
import com.quyennv.lms.dto.BaseResponse;
import com.quyennv.lms.dto.assignment.*;
import com.quyennv.lms.entities.Assignment;
import com.quyennv.lms.security.UserPrincipal;
import com.quyennv.lms.service.AssignmentService;
import com.quyennv.lms.service.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/assignments")
public class AssignmentController {

    private final BaseService baseService;

    private final AssignmentService assignmentService;

    public AssignmentController(BaseService baseService, AssignmentService assignmentService) {
        this.baseService = baseService;
        this.assignmentService = assignmentService;
    }

    @PostMapping
    @LogsActivityAnnotation
    public BaseResponse<CreateAssignmentResponse> createAssignment(
            @CurrentUser UserPrincipal requester,
            @RequestBody @Valid CreateAssignmentRequest request) {
        return baseService.ofSucceeded(assignmentService.create(request, requester));
    }

    @GetMapping("/{id}")
    public BaseResponse<Assignment> getAssignment(
            @CurrentUser UserPrincipal requester,
            @PathVariable String id) {
        return baseService.ofSucceeded(assignmentService.getAssignment(UUID.fromString(id), requester));
    }

    @PutMapping("/{id}")
    public BaseResponse<CreateAssignmentResponse> update(
            @CurrentUser UserPrincipal requester,
            @PathVariable String id,
            @RequestBody @Valid UpdateAssignmentRequest request) {
        request.setAssignmentId(UUID.fromString(id));
        return baseService.ofSucceeded(assignmentService.update(request, requester));
    }

}
