package com.quyennv.lms.repository.impl;

import com.quyennv.lms.entities.Question;
import com.quyennv.lms.entities.QuestionChoice;
import com.quyennv.lms.entities.QuestionTextAnswer;
import com.quyennv.lms.mappers.QuestionChoiceMapper;
import com.quyennv.lms.mappers.QuestionMapper;
import com.quyennv.lms.repository.QuestionRepository;
import com.quyennv.lms.repository.QuestionTextAnswerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository {

    private final QuestionMapper questionMapper;

    public QuestionRepositoryImpl(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    @Override
    public int save(Question record) {
        record.setId(UUID.randomUUID());
        questionMapper.insert(record);
        return 1;
    }

    @Override
    public Question findById(String id) {
        return null;
    }

    @Override
    public int update(Question updateData) {
        return questionMapper.updateByPrimaryKey(updateData);
    }

    @Override
    public void softDelete(UUID questionId) {
        questionMapper.softDeleteByPrimaryKey(questionId);
    }
}
