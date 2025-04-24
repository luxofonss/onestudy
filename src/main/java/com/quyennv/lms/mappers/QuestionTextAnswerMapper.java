package com.quyennv.lms.mappers;

import com.quyennv.lms.entities.QuestionTextAnswer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface QuestionTextAnswerMapper {

    int deleteByPrimaryKey(Object questionId);

    int insert(QuestionTextAnswer record);

    int insertSelective(QuestionTextAnswer record);

    QuestionTextAnswer selectByPrimaryKey(Object questionId);

    int updateByPrimaryKeySelective(QuestionTextAnswer record);

    int updateByPrimaryKey(QuestionTextAnswer record);

    int insertBatch(List<QuestionTextAnswer> records);

    List<QuestionTextAnswer> selectByQuestionId(UUID questionId);

}