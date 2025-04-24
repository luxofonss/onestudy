package com.quyennv.lms.mappers;

import com.quyennv.lms.entities.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface CourseMapper {

    int deleteByPrimaryKey(Object id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Object id);

    List<Course> selectByTeacherId(UUID id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    List<Course> selectRegisteredCourses(UUID userId);

    Course selectByCode(String code);

    Course selectByLessonId(UUID lessonId);

}