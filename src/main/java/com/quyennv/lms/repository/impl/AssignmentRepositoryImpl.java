package com.quyennv.lms.repository.impl;

import com.quyennv.lms.entities.Assignment;
import com.quyennv.lms.mappers.AssignmentMapper;
import com.quyennv.lms.repository.AssignmentRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class AssignmentRepositoryImpl implements AssignmentRepository {

    private final AssignmentMapper assignmentMapper;

    public AssignmentRepositoryImpl(AssignmentMapper assignmentMapper) {
        this.assignmentMapper = assignmentMapper;
    }

    @Override
    public int save(Assignment assignment) {
        assignment.setId(UUID.randomUUID());
        return assignmentMapper.insert(assignment);
    }

    @Override
    public Assignment findById(UUID id) {
        return assignmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateSelective(Assignment assignment) {
        return assignmentMapper.updateByPrimaryKeySelective(assignment);
    }

    @Override
    public void softDeleteByLessonId(UUID lessonId) {
        assignmentMapper.softDeleteByLessonId(lessonId);
    }

    @Override
    public Assignment findByLessonId(UUID lessonId) {
        return assignmentMapper.selectByLessonId(lessonId);
    }
}
