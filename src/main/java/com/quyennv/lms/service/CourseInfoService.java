package com.quyennv.lms.service;

import com.quyennv.lms.entities.CourseInfo;

import java.util.List;
import java.util.UUID;

public interface CourseInfoService {

    void updateListByCourseId(UUID courseId, List<CourseInfo> courseInfos);

    List<CourseInfo> getByCourseId(UUID courseId);

}
