package com.quyennv.lms.mappers;

import com.quyennv.lms.entities.Conversation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConversationMapper {

    int deleteByPrimaryKey(Object id);

    int insert(Conversation record);

    int insertSelective(Conversation record);

    Conversation selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(Conversation record);

    int updateByPrimaryKey(Conversation record);
}