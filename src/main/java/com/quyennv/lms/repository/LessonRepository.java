package com.quyennv.lms.repository;

import com.quyennv.lms.entities.Lesson;

import java.util.List;
import java.util.UUID;

public interface LessonRepository {

    int insert(Lesson lesson);

    void insertBatch(List<Lesson> lessons);

    int insertSelective(Lesson lesson);

    Lesson findByPrimaryKey(UUID id);

    List<Lesson> findBySectionId(UUID sectionId);

    int updateByPrimaryKeySelective(Lesson lesson);

    int updateByPrimaryKey(Lesson lesson);

    int softDeleteByPrimaryKey(UUID id);
}
