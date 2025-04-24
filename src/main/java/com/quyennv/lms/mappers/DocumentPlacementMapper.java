package com.quyennv.lms.mappers;

import com.quyennv.lms.entities.DocumentPlacement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DocumentPlacementMapper {

    int deleteByPrimaryKey(Object id);

    int insert(DocumentPlacement record);

    int insertSelective(DocumentPlacement record);

    DocumentPlacement selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(DocumentPlacement record);

    int updateByPrimaryKey(DocumentPlacement record);
}