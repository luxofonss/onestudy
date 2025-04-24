package com.quyennv.lms.repository;

import com.quyennv.lms.entities.Question;

public interface QuestionRepository {

    int save(Question record);

    Question findById(String id);

    int update(Question updateData);

}
