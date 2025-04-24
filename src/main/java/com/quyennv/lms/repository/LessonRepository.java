package com.quyennv.lms.repository;

import com.quyennv.lms.entities.Lesson;

import java.util.List;
import java.util.UUID;

public interface LessonRepository {

    List<Lesson> findLessonsBySectionId(UUID sectionId);

}
