package com.quyennv.lms.repository;

import com.quyennv.lms.entities.Section;

import java.util.List;
import java.util.UUID;

public interface SectionRepository {

    void persist(Section section);

    int update(Section section);

    List<Section> findByCourseId(UUID courseId);

    void delete(Section section);

}
