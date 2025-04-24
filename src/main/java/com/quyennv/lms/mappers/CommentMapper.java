package com.quyennv.lms.mappers;

import com.quyennv.lms.entities.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {

    int deleteByPrimaryKey(Object id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}