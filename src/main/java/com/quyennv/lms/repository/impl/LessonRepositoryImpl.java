package com.quyennv.lms.repository.impl;

import com.quyennv.lms.entities.Lesson;
import com.quyennv.lms.mappers.LessonMapper;
import com.quyennv.lms.repository.LessonRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class LessonRepositoryImpl implements LessonRepository {

    private final LessonMapper lessonMapper;

    public LessonRepositoryImpl(LessonMapper lessonMapper) {
        this.lessonMapper = lessonMapper;
    }

    @Override
    public List<Lesson> findLessonsBySectionId(UUID sectionId) {
        return lessonMapper.selectBySectionId(sectionId);
    }
}
