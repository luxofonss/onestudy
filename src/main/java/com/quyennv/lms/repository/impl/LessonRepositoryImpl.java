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
    public int insert(Lesson lesson) {
        lesson.setId(UUID.randomUUID());
        return lessonMapper.insert(lesson);
    }

    @Override
    public void insertBatch(List<Lesson> lessons) {
        // todo: handle bath size
        lessonMapper.insertBatch(lessons);
    }

    @Override
    public int insertSelective(Lesson lesson) {
        return lessonMapper.insertSelective(lesson);
    }

    @Override
    public Lesson findByPrimaryKey(UUID id) {
        return lessonMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Lesson> findBySectionId(UUID sectionId) {
        return lessonMapper.selectBySectionId(sectionId);
    }

    @Override
    public int updateByPrimaryKeySelective(Lesson lesson) {
        return lessonMapper.updateByPrimaryKeySelective(lesson);
    }

    @Override
    public int updateByPrimaryKey(Lesson lesson) {
        return lessonMapper.updateByPrimaryKey(lesson);
    }

    @Override
    public int softDeleteByPrimaryKey(UUID id) {
        return lessonMapper.softDeleteByPrimaryKey(id);
    }
}
