package com.quyennv.lms.mappers;

import com.quyennv.lms.entities.LearnerInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LearnerInfoMapper {

    int deleteByPrimaryKey(Object id);

    int insert(LearnerInfo record);

    int insertSelective(LearnerInfo record);

    LearnerInfo selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(LearnerInfo record);

    int updateByPrimaryKey(LearnerInfo record);
}