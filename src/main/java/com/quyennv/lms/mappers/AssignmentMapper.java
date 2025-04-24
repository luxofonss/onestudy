package com.quyennv.lms.mappers;

import com.quyennv.lms.entities.Assignment;
import org.apache.ibatis.annotations.Mapper;

import java.util.UUID;

@Mapper
public interface AssignmentMapper {

    int deleteByPrimaryKey(Object id);

    int insert(Assignment record);

    int insertSelective(Assignment record);

    Assignment selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(Assignment record);

    int updateByPrimaryKey(Assignment record);

    void softDeleteByLessonId(UUID id);
}