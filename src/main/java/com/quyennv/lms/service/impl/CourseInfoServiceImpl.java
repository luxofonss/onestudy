package com.quyennv.lms.service.impl;

import com.quyennv.lms.entities.CourseInfo;
import com.quyennv.lms.repository.CourseInfoRepository;
import com.quyennv.lms.service.CourseInfoService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseInfoServiceImpl implements CourseInfoService {

    private final CourseInfoRepository courseInfoRepository;

    public CourseInfoServiceImpl(CourseInfoRepository courseInfoRepository) {
        this.courseInfoRepository = courseInfoRepository;
    }


    @Override
    public void updateListByCourseId(UUID courseId, List<CourseInfo> courseInfos) {
        List<CourseInfo> newCourseInfos = courseInfos.stream().filter(item -> item.getId() == null).toList();
        List<CourseInfo> updatedCourseInfos = courseInfos.stream().filter(item -> item.getId() != null).toList();

        courseInfoRepository.deleteNotInIds(updatedCourseInfos.stream().map(CourseInfo::getId).collect(Collectors.toList()));

        if (!CollectionUtils.isEmpty(newCourseInfos)) {
            newCourseInfos.forEach(courseInfo -> {
                courseInfo.setCourseId(courseId);
                courseInfo.setId(UUID.randomUUID());
            });
            courseInfoRepository.insertBatch(newCourseInfos);
        }

        if (!CollectionUtils.isEmpty(updatedCourseInfos)) {
            updatedCourseInfos.forEach(courseInfoRepository::updateByPrimaryKeySelective);
            updatedCourseInfos.forEach(courseInfo -> {
                courseInfo.setCourseId(courseId);
                courseInfoRepository.updateByPrimaryKeySelective(courseInfo);
            });
        }
    }

    @Override
    public List<CourseInfo> getByCourseId(String courseId) {
        List<CourseInfo> courseInfos = courseInfoRepository.findByCourseId(courseId);
        if (CollectionUtils.isEmpty(courseInfos)) {
            return List.of();
        }
        return courseInfos;
    }
}
