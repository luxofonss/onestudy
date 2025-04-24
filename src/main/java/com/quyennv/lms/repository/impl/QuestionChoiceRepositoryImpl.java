package com.quyennv.lms.repository.impl;

import com.quyennv.lms.entities.QuestionChoice;
import com.quyennv.lms.mappers.QuestionChoiceMapper;
import com.quyennv.lms.repository.QuestionChoiceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class QuestionChoiceRepositoryImpl implements QuestionChoiceRepository {

    private final QuestionChoiceMapper questionChoiceMapper;

    public QuestionChoiceRepositoryImpl(QuestionChoiceMapper questionChoiceMapper) {
        this.questionChoiceMapper = questionChoiceMapper;
    }

    @Override
    public int save(QuestionChoice record) {
        return questionChoiceMapper.insert(record);
    }

    @Override
    public int insertBatch(List<QuestionChoice> records) {
        return questionChoiceMapper.insertBatch(records);
    }

    @Override
    public QuestionChoice findById(UUID id) {
        return questionChoiceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(QuestionChoice record) {
        return questionChoiceMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateSelective(QuestionChoice record) {
        return questionChoiceMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(UUID id) {
        return questionChoiceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<QuestionChoice> findByQuestionId(List<UUID> questionIds) {
        return questionChoiceMapper.selectByQuestionIds(questionIds);
    }
}
