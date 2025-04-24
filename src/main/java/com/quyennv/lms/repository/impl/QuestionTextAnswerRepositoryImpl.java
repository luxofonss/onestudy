package com.quyennv.lms.repository.impl;

import com.quyennv.lms.entities.QuestionTextAnswer;
import com.quyennv.lms.mappers.QuestionTextAnswerMapper;
import com.quyennv.lms.repository.QuestionTextAnswerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class QuestionTextAnswerRepositoryImpl implements QuestionTextAnswerRepository {

    private final QuestionTextAnswerMapper questionTextAnswerMapper;

    public QuestionTextAnswerRepositoryImpl(QuestionTextAnswerMapper questionTextAnswerMapper) {
        this.questionTextAnswerMapper = questionTextAnswerMapper;
    }

    @Override
    public int save(QuestionTextAnswer record) {
        return questionTextAnswerMapper.insert(record);
    }

    @Override
    public int insertBatch(List<QuestionTextAnswer> records) {
        return questionTextAnswerMapper.insertBatch(records);
    }

    @Override
    public QuestionTextAnswer findById(String id) {
        return questionTextAnswerMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(QuestionTextAnswer record) {
        return questionTextAnswerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(String id) {
        return questionTextAnswerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<QuestionTextAnswer> findByQuestionId(UUID questionId) {
        return questionTextAnswerMapper.selectByQuestionId(questionId);
    }
}
