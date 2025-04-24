package com.quyennv.lms.service;

import com.quyennv.lms.entities.Question;

import java.util.List;
import java.util.UUID;

public interface QuestionService {

    void deleteByAssignmentId(String assignmentId);

    List<Question> getByAssignmentId(UUID id);
}
