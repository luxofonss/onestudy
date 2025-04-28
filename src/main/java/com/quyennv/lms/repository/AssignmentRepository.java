package com.quyennv.lms.repository;

import com.quyennv.lms.entities.Assignment;

import java.util.UUID;

public interface AssignmentRepository {

    int save(Assignment assignment);

    Assignment findById(UUID id);

    int updateSelective(Assignment assignment);

    void softDeleteByLessonId(UUID lessonId);

    Assignment findByLessonId(UUID uuid);
}
