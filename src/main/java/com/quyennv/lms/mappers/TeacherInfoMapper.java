package com.quyennv.lms.mappers;

import com.quyennv.lms.entities.TeacherInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherInfoMapper {

    int deleteByPrimaryKey(Object id);

    int insert(TeacherInfo record);

    int insertSelective(TeacherInfo record);

    TeacherInfo selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(TeacherInfo record);

    int updateByPrimaryKey(TeacherInfo record);
}