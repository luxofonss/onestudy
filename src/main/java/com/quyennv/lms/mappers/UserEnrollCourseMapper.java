package com.quyennv.lms.mappers;

import com.quyennv.lms.entities.UserEnrollCourse;
import com.quyennv.lms.entities.UserEnrollCourseKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface UserEnrollCourseMapper {

    int deleteByPrimaryKey(UserEnrollCourseKey key);

    int insert(UserEnrollCourse record);

    int insertSelective(UserEnrollCourse record);

    UserEnrollCourse selectByPrimaryKey(UserEnrollCourseKey key);

    int updateByPrimaryKeySelective(UserEnrollCourse record);

    int updateByPrimaryKey(UserEnrollCourse record);

    UserEnrollCourse selectByPrimaryKeyAndStatus(UserEnrollCourseKey key, String status);

    List<UserEnrollCourse> selectByUserId(UUID userId);

    List<UserEnrollCourse> selectByCourseId(UUID courseId);

    int insertBatch(List<UserEnrollCourse> records);

    List<UserEnrollCourse> selectByStudentIds(List<UUID> userIds);
}