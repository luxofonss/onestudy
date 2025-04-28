package com.quyennv.lms.mappers;

import com.quyennv.lms.entities.CourseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface CourseInfoMapper {

    int deleteByPrimaryKey(Object id);

    int insert(CourseInfo record);

    int insertBatch(List<CourseInfo> records);

    int insertSelective(CourseInfo record);

    CourseInfo selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(CourseInfo record);

    int updateByPrimaryKey(CourseInfo record);

    List<CourseInfo> selectByCourseId(UUID courseId);

    void deleteCourseIdNotIn(List<UUID> ids, UUID courseId);
}