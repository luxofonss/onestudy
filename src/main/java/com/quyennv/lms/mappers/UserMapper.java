package com.quyennv.lms.mappers;

import com.quyennv.lms.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(UUID id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(UUID id);

    User selectByUsername(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectByUserIdList(List<UUID> userIds);
}