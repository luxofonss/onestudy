package com.quyennv.lms.service.impl;

import com.quyennv.lms.dto.BasicCreationResponse;
import com.quyennv.lms.dto.course.CourseSectionCreateRequest;
import com.quyennv.lms.dto.course.CourseSectionUpdateRequest;
import com.quyennv.lms.dto.mappers.CourseSectionDtoMapper;
import com.quyennv.lms.entities.Section;
import com.quyennv.lms.repository.SectionRepository;
import com.quyennv.lms.security.UserPrincipal;
import com.quyennv.lms.service.CourseLessonService;
import com.quyennv.lms.service.CourseSectionService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

@Service
public class CourseSectionServiceImpl implements CourseSectionService {

    private final SectionRepository sectionRepository;

    private final CourseLessonService lessonService;

    private final CourseSectionDtoMapper courseSectionDtoMapper;

    public CourseSectionServiceImpl(SectionRepository sectionRepository,
                                    CourseLessonService lessonService,
                                    CourseSectionDtoMapper courseSectionDtoMapper) {
        this.sectionRepository = sectionRepository;
        this.lessonService = lessonService;
        this.courseSectionDtoMapper = courseSectionDtoMapper;
    }

    @Override
    public void updateListByCourseId(UUID courseId, List<Section> courseSections) {
        List<Section> newSections = courseSections.stream().filter(item -> item.getId() == null).toList();
        List<Section> updatedSections = courseSections.stream().filter(item -> item.getId() != null).toList();
        List<Section> oldSections = sectionRepository.findByCourseId(courseId);

        if (!CollectionUtils.isEmpty(oldSections)) {
            oldSections.forEach(section -> {
                if (courseSections.stream().noneMatch(item -> item.getId() != null && item.getId().equals(section.getId()))) {
                    sectionRepository.delete(section);
                }
            });
        }

        if (!CollectionUtils.isEmpty(newSections)) {
            newSections.forEach(section -> {
                section.setCourseId(courseId);
                section.setId(UUID.randomUUID());
                sectionRepository.persist(section);
            });
        }

        if (!CollectionUtils.isEmpty(updatedSections)) {
            updatedSections.forEach(section -> {
                section.setCourseId(courseId);
                sectionRepository.update(section);


                if (!CollectionUtils.isEmpty(section.getLessons())) {
                    lessonService.updateLessons(section.getLessons(), section.getId());
                }
            });
        }
    }

    @Override
    public List<Section> getByCourseId(UUID courseId) {
        List<Section> sections = sectionRepository.findByCourseId(courseId);
        if (CollectionUtils.isEmpty(sections)) {
            return List.of();
        }
        return sections;
    }

    @Override
    public BasicCreationResponse create(CourseSectionCreateRequest request, UserPrincipal requester) {
        // check permission
        Section section = courseSectionDtoMapper.toSection(request);
        sectionRepository.persist(section);
        return BasicCreationResponse.builder().id(section.getId().toString()).build();
    }

    @Override
    public BasicCreationResponse update(CourseSectionUpdateRequest request, UserPrincipal requester) {
        // check permission
        Section section = courseSectionDtoMapper.toSectionUpdate(request);
        sectionRepository.update(section);
        return BasicCreationResponse.builder().id(section.getId().toString()).build();
    }

    @Override
    public void delete(CourseSectionUpdateRequest request, UserPrincipal requester) {
        // check permission
        Section section = courseSectionDtoMapper.toSectionUpdate(request);
        sectionRepository.delete(section);
    }
}
