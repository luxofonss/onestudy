package com.quyennv.lms.mappers;

import com.quyennv.lms.entities.Lesson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LessonMapper {

    int softDeleteByPrimaryKey(Object id);

    int insert(Lesson record);

    void insertBatch(List<Lesson> lessons);

    int insertSelective(Lesson record);

    Lesson selectByPrimaryKey(Object id);

    List<Lesson> selectBySectionId(Object sectionId);

    int updateByPrimaryKeySelective(Lesson record);

    int updateByPrimaryKey(Lesson record);

}