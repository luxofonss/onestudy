package com.quyennv.lms.mappers;

import com.quyennv.lms.entities.QuestionAnswer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionAnswerMapper {

    int deleteByPrimaryKey(Object id);

    int insert(QuestionAnswer record);

    int insertSelective(QuestionAnswer record);

    QuestionAnswer selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(QuestionAnswer record);

    int updateByPrimaryKey(QuestionAnswer record);
}