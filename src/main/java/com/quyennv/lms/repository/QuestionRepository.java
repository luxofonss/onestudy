package com.quyennv.lms.repository;

import com.quyennv.lms.entities.Question;

import java.util.UUID;

public interface QuestionRepository {

    int save(Question record);

    Question findById(String id);

    int update(Question updateData);

    void softDelete(UUID questionId);
}
