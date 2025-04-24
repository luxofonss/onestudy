package com.quyennv.lms.service;

import com.quyennv.lms.dto.assignment.CreateQuestionRequest;
import com.quyennv.lms.dto.assignment.UpdateQuestionRequest;
import com.quyennv.lms.security.UserPrincipal;

import javax.validation.Valid;

public interface AssignmentQuestionService {

    void addQuestion(@Valid CreateQuestionRequest request, UserPrincipal requester);

    void updateQuestion(@Valid UpdateQuestionRequest request, UserPrincipal requester);

}
