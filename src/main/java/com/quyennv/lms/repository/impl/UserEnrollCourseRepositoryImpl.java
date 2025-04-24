package com.quyennv.lms.repository.impl;

import com.quyennv.lms.entities.UserEnrollCourse;
import com.quyennv.lms.entities.UserEnrollCourseKey;
import com.quyennv.lms.mappers.UserEnrollCourseMapper;
import com.quyennv.lms.repository.UserEnrollCourseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserEnrollCourseRepositoryImpl implements UserEnrollCourseRepository {

    private final UserEnrollCourseMapper userEnrollCourseMapper;

    public UserEnrollCourseRepositoryImpl(UserEnrollCourseMapper userEnrollCourseMapper) {
        this.userEnrollCourseMapper = userEnrollCourseMapper;
    }

    @Override
    public int save(UserEnrollCourse userEnrollCourse) {
        userEnrollCourse.setId(UUID.randomUUID());
        return userEnrollCourseMapper.insert(userEnrollCourse);
    }

    @Override
    public int saveBatch(List<UserEnrollCourse> registrations) {
        return userEnrollCourseMapper.insertBatch(registrations);
    }

    @Override
    public int updateByPrimaryKey(UserEnrollCourse record) {
        return userEnrollCourseMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<UserEnrollCourse> getByStudentIds(List<UUID> userIds) {
        return userEnrollCourseMapper.selectByStudentIds(userIds);
    }

    @Override
    public Optional<UserEnrollCourse> findByCourseIdAndUserId(UUID courseId, UUID userId) {
        UserEnrollCourseKey key = new UserEnrollCourseKey();
        key.setCourseId(courseId);
        key.setUserId(userId);

        return Optional.ofNullable(userEnrollCourseMapper.selectByPrimaryKey(key));
    }

    @Override
    public Optional<UserEnrollCourse> findByCourseIdAndUserIdAndStatus(UUID courseId, UUID userId, String status) {
        return Optional.ofNullable(userEnrollCourseMapper.selectByPrimaryKeyAndStatus(
                UserEnrollCourseKey.builder()
                    .courseId(courseId)
                    .userId(userId)
                    .build(), status)
        );
    }

    @Override
    public List<UserEnrollCourse> findByUserId(UUID userId) {
        return userEnrollCourseMapper.selectByUserId(userId);
    }

    @Override
    public List<UserEnrollCourse> findByCourseId(UUID courseId) {
        return userEnrollCourseMapper.selectByCourseId(courseId);
    }

}
