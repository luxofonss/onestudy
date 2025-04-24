package com.quyennv.lms.mappers;

import com.quyennv.lms.entities.Auth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuthMapper {

    int deleteByPrimaryKey(Object userId);

    int insert(Auth record);

    int insertSelective(Auth record);

    Auth selectByPrimaryKey(Object userId);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    Auth selectByUserId(Object id);
}