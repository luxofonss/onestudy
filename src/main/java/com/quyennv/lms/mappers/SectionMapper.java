package com.quyennv.lms.mappers;

import com.quyennv.lms.entities.Section;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface SectionMapper {

    int deleteByPrimaryKey(Object id);

    int insert(Section record);

    int insertSelective(Section record);

    Section selectByPrimaryKey(Object id);

    List<Section> selectByCourseId(UUID courseId);

    int updateByPrimaryKeySelective(Section record);

    int updateByPrimaryKey(Section record);

}