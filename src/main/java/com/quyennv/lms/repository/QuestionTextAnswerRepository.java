package com.quyennv.lms.repository;

import com.quyennv.lms.entities.QuestionTextAnswer;

import java.util.List;
import java.util.UUID;

public interface QuestionTextAnswerRepository {

    int save(QuestionTextAnswer record);

    int insertBatch(List<QuestionTextAnswer> records);

    QuestionTextAnswer findById(String id);

    int update(QuestionTextAnswer record);

    int delete(UUID id);

    List<QuestionTextAnswer> findByQuestionId(UUID questionId);

}
