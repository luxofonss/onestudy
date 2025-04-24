package com.quyennv.lms.mappers;

import com.quyennv.lms.entities.React;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReactMapper {

    int deleteByPrimaryKey(Object id);

    int insert(React record);

    int insertSelective(React record);

    React selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(React record);

    int updateByPrimaryKey(React record);
}