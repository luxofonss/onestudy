package com.quyennv.lms.repository;

import com.quyennv.lms.entities.CourseInfo;

import java.util.List;
import java.util.UUID;

public interface CourseInfoRepository {

    List<CourseInfo> findByCourseId(String courseId);

    void deleteNotInIds(List<UUID> ids);

    void insertBatch(List<CourseInfo> courseInfos);

    void updateByPrimaryKeySelective(CourseInfo courseInfo);

}
