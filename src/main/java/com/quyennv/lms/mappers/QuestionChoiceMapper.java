package com.quyennv.lms.mappers;

import com.quyennv.lms.entities.QuestionChoice;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionChoiceMapper {

    int deleteByPrimaryKey(Object id);

    int insert(QuestionChoice record);

    int insertSelective(QuestionChoice record);

    QuestionChoice selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(QuestionChoice record);

    int updateByPrimaryKey(QuestionChoice record);

    int insertBatch(List<QuestionChoice> records);

    List<QuestionChoice> selectByQuestionId(UUID uuid);

    List<QuestionChoice> selectByQuestionIds(List<UUID> questionIds);
}