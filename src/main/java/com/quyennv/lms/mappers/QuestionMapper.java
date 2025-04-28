package com.quyennv.lms.mappers;

import com.quyennv.lms.entities.Question;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QuestionMapper {

    int deleteByPrimaryKey(UUID id);

    int softDeleteByPrimaryKey(UUID id);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(UUID id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);

    void deleteByAssignmentId(String assignmentId);

    List<Question> selectByAssignmentId(UUID id);
}