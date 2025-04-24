package com.quyennv.lms.service;

import com.quyennv.lms.dto.BasicCreationResponse;
import com.quyennv.lms.dto.course.CourseSectionCreateRequest;
import com.quyennv.lms.dto.course.CourseSectionUpdateRequest;
import com.quyennv.lms.entities.Section;
import com.quyennv.lms.security.UserPrincipal;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface CourseSectionService {

    void updateListByCourseId(UUID courseId, List<Section> courseSections);

    List<Section> getByCourseId(UUID courseId);

    BasicCreationResponse create(@Valid CourseSectionCreateRequest request, UserPrincipal requester);

    BasicCreationResponse update(@Valid CourseSectionUpdateRequest request, UserPrincipal requester);

    void delete(@Valid CourseSectionUpdateRequest request, UserPrincipal requester);

}
