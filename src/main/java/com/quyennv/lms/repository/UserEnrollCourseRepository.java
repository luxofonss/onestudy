package com.quyennv.lms.repository;

import com.quyennv.lms.dto.auth.AuthProfileResponse;
import com.quyennv.lms.entities.UserEnrollCourse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserEnrollCourseRepository {

    int save(UserEnrollCourse userEnrollCourse);

    Optional<UserEnrollCourse> findByCourseIdAndUserId(UUID courseId, UUID userId);

    Optional<UserEnrollCourse> findByCourseIdAndUserIdAndStatus(UUID courseId, UUID userId, String status);

    List<UserEnrollCourse> findByUserId(UUID userId);

    List<UserEnrollCourse> findByCourseId(UUID courseId);

    int saveBatch(List<UserEnrollCourse> registrations);

    int updateByPrimaryKey(UserEnrollCourse record);

    List<UserEnrollCourse> getByStudentIds(List<UUID> userIds);
}
