package com.quyennv.lms.mappers;

import com.quyennv.lms.entities.AssignmentAttempt;

public interface AssignmentAttemptMapper {

    int deleteByPrimaryKey(Object id);

    int insert(AssignmentAttempt record);

    int insertSelective(AssignmentAttempt record);

    AssignmentAttempt selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(AssignmentAttempt record);

    int updateByPrimaryKey(AssignmentAttempt record);
}