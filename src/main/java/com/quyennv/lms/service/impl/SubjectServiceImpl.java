package com.quyennv.lms.service.impl;

import com.quyennv.lms.entities.Subject;
import com.quyennv.lms.repository.SubjectRepository;
import com.quyennv.lms.service.SubjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.getAll();
    }
}
