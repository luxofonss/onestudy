package com.quyennv.lms.repository.impl;

import com.quyennv.lms.entities.Subject;
import com.quyennv.lms.mappers.SubjectMapper;
import com.quyennv.lms.repository.SubjectRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubjectRepositoryImpl implements SubjectRepository {

    private final SubjectMapper subjectMapper;

    public SubjectRepositoryImpl(SubjectMapper subjectMapper) {
        this.subjectMapper = subjectMapper;
    }

    @Override
    public List<Subject> getAll() {
        return subjectMapper.selectAll();
    }
}
