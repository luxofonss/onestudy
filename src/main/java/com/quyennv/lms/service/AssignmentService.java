package com.quyennv.lms.service;

import com.quyennv.lms.dto.assignment.*;
import com.quyennv.lms.entities.Assignment;
import com.quyennv.lms.security.UserPrincipal;

import javax.validation.Valid;
import java.util.UUID;

public interface AssignmentService {

    CreateAssignmentResponse create(CreateAssignmentRequest request, UserPrincipal requester);

    CreateAssignmentResponse update(UpdateAssignmentRequest request, UserPrincipal requester);

    void softDeleteAssignmentInLesson(UUID lessonId);

    Assignment getAssignment(UUID id, UserPrincipal requester);

    Assignment checkAssignmentPermission(UUID assignmentId, UserPrincipal requester);

    Assignment getAssignmentByLesson(UUID uuid, UserPrincipal requester);
}
