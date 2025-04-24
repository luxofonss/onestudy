package com.quyennv.lms.repository;

import com.quyennv.lms.entities.QuestionChoice;

import java.util.List;
import java.util.UUID;

public interface QuestionChoiceRepository {

    int save(QuestionChoice record);

    int insertBatch(List<QuestionChoice> records);

    QuestionChoice findById(UUID id);

    int update(QuestionChoice record);

    int updateSelective(QuestionChoice record);

    int delete(UUID id);

    List<QuestionChoice> findByQuestionId(List<UUID> questionIds);

}
