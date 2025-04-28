package com.quyennv.lms.controller;

import com.quyennv.lms.annotations.CurrentUser;
import com.quyennv.lms.dto.BaseResponse;
import com.quyennv.lms.dto.assignment.DeleteQuestionRequest;
import com.quyennv.lms.dto.assignment.QuestionMutationRequest;
import com.quyennv.lms.entities.Question;
import com.quyennv.lms.exception.BusinessException;
import com.quyennv.lms.security.UserPrincipal;
import com.quyennv.lms.service.AssignmentQuestionService;
import com.quyennv.lms.service.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/assignments/{assignmentId}/questions")
public class AssignmentQuestionController {

    private final BaseService baseService;

    private final AssignmentQuestionService assignmentQuestionService;

    public AssignmentQuestionController(BaseService baseService, AssignmentQuestionService assignmentQuestionService) {
        this.baseService = baseService;
        this.assignmentQuestionService = assignmentQuestionService;
    }


    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public BaseResponse<Question> addQuestion(
            @CurrentUser UserPrincipal requester,
            @PathVariable String assignmentId,
            @RequestBody @Valid QuestionMutationRequest request) {
        request.setAssignmentId(UUID.fromString(assignmentId));
        return baseService.ofSucceeded(assignmentQuestionService.addQuestion(request, requester));
    }

    @PutMapping("/{questionId}")
    public BaseResponse<?> updateQuestion(
            @CurrentUser UserPrincipal requester,
            @PathVariable String assignmentId,
            @PathVariable String questionId,
            @RequestBody @Valid QuestionMutationRequest request) {
        request.setAssignmentId(UUID.fromString(assignmentId));
        request.setQuestionId(UUID.fromString(questionId));
        try {
            return baseService.ofSucceeded(assignmentQuestionService.updateQuestion(request, requester, false));
        } catch (Exception e) {
            return baseService.ofFailed(new BusinessException(1, e));
        }
    }

    @DeleteMapping("/{questionId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void deleteQuestion(
            @CurrentUser UserPrincipal requester,
            @PathVariable UUID assignmentId,
            @PathVariable UUID questionId) {
        DeleteQuestionRequest request = new DeleteQuestionRequest();
        request.setAssignmentId(assignmentId);
        request.setQuestionId(questionId);
        try {
            assignmentQuestionService.deleteQuestion(request);
        } catch (Exception e) {
            baseService.ofFailed(new BusinessException(1, e));
        }
    }

}
