package com.quyennv.lms.mappers;

import com.quyennv.lms.entities.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SubjectMapper {

    int deleteByPrimaryKey(Object id);

    int insert(Subject record);

    int insertSelective(Subject record);

    Subject selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(Subject record);

    int updateByPrimaryKey(Subject record);

    List<Subject> selectAll();
}