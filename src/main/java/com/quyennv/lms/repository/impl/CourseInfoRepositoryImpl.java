package com.quyennv.lms.repository.impl;

import com.quyennv.lms.entities.CourseInfo;
import com.quyennv.lms.mappers.CourseInfoMapper;
import com.quyennv.lms.repository.CourseInfoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CourseInfoRepositoryImpl implements CourseInfoRepository {

    private final CourseInfoMapper courseInfoMapper;

    public CourseInfoRepositoryImpl(CourseInfoMapper courseInfoMapper) {
        this.courseInfoMapper = courseInfoMapper;
    }

    @Override
    public List<CourseInfo> findByCourseId(String courseId) {
        return courseInfoMapper.selectByCourseId(courseId);
    }

    @Override
    public void deleteNotInIds(List<UUID> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }

        courseInfoMapper.deleteCourseIdNotIn(ids);
    }

    @Override
    public void insertBatch(List<CourseInfo> courseInfos) {
        if (courseInfos == null || courseInfos.isEmpty()) {
            return;
        }

        courseInfoMapper.insertBatch(courseInfos);
    }

    @Override
    public void updateByPrimaryKeySelective(CourseInfo courseInfo) {
        if (courseInfo == null) {
            return;
        }

        courseInfoMapper.updateByPrimaryKeySelective(courseInfo);
    }
}
