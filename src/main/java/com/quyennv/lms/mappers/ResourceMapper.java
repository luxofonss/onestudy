package com.quyennv.lms.mappers;

import com.quyennv.lms.entities.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResourceMapper {

    int deleteByPrimaryKey(Object id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);
}