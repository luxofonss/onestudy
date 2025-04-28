package com.quyennv.lms.service;

import com.quyennv.lms.dto.assignment.DeleteQuestionRequest;
import com.quyennv.lms.dto.assignment.QuestionMutationRequest;
import com.quyennv.lms.entities.Question;
import com.quyennv.lms.security.UserPrincipal;

import javax.validation.Valid;

public interface AssignmentQuestionService {

    Question addQuestion(@Valid QuestionMutationRequest request, UserPrincipal requester);

    Question updateQuestion(@Valid QuestionMutationRequest request, UserPrincipal requester, boolean isInternal);

    void deleteQuestion(DeleteQuestionRequest request);
}
