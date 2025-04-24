package com.quyennv.lms.controller;

import com.quyennv.lms.annotations.CurrentUser;
import com.quyennv.lms.dto.assignment.CreateQuestionRequest;
import com.quyennv.lms.dto.assignment.UpdateQuestionRequest;
import com.quyennv.lms.security.UserPrincipal;
import com.quyennv.lms.service.AssignmentQuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/assignments/{assignmentId}/questions")
public class AssignmentQuestionController {

    private final AssignmentQuestionService assignmentQuestionService;

    public AssignmentQuestionController(AssignmentQuestionService assignmentQuestionService) {
        this.assignmentQuestionService = assignmentQuestionService;
    }


    @PostMapping("/{id}/questions")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addQuestion(
            @CurrentUser UserPrincipal requester,
            @PathVariable String assignmentId,
            @RequestBody @Valid CreateQuestionRequest request) {
        request.setAssignmentId(UUID.fromString(assignmentId));
        assignmentQuestionService.addQuestion(request, requester);
    }

    @PutMapping("/{id}/questions/{questionId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void updateQuestion(
            @CurrentUser UserPrincipal requester,
            @PathVariable String assignmentId,
            @PathVariable String questionId,
            @RequestBody @Valid UpdateQuestionRequest request) {
        request.setAssignmentId(UUID.fromString(assignmentId));
        request.setQuestionId(UUID.fromString(questionId));
        assignmentQuestionService.updateQuestion(request, requester);
    }

}
