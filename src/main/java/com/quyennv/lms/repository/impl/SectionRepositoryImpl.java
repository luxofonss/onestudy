package com.quyennv.lms.repository.impl;

import com.quyennv.lms.entities.Lesson;
import com.quyennv.lms.entities.Section;
import com.quyennv.lms.mappers.LessonMapper;
import com.quyennv.lms.mappers.SectionMapper;
import com.quyennv.lms.repository.SectionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

@Repository
public class SectionRepositoryImpl implements SectionRepository {

    private final SectionMapper sectionMapper;

    private final LessonMapper lessonMapper;

    public SectionRepositoryImpl(SectionMapper sectionMapper, LessonMapper lessonMapper) {
        this.sectionMapper = sectionMapper;
        this.lessonMapper = lessonMapper;
    }

    @Override
    public void persist(Section section) {
        section.setId(UUID.randomUUID());
        sectionMapper.insert(section);

        if (!CollectionUtils.isEmpty(section.getLessons())) {
            section.getLessons().forEach(lesson -> {
                lesson.setId(UUID.randomUUID());
                lesson.setSectionId(section.getId());
            });
            lessonMapper.insertBatch(section.getLessons());
        }

    }

    @Override
    public int update(Section section) {
        sectionMapper.updateByPrimaryKeySelective(section);
        return 1;
    }

    @Override
    public List<Section> findByCourseId(UUID courseId) {
        return sectionMapper.selectByCourseId(courseId);
    }

    @Override
    public void delete(Section section) {
        sectionMapper.deleteByPrimaryKey(section.getId());
        List<Lesson> lessons = lessonMapper.selectBySectionId(section.getId());
        if (!CollectionUtils.isEmpty(lessons)) {
            lessons.forEach(lesson -> lessonMapper.softDeleteByPrimaryKey(lesson.getId()));
        }

    }

}
