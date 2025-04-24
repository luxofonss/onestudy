package com.quyennv.lms.dto.mappers;

import com.quyennv.lms.dto.course.CourseSectionCreateRequest;
import com.quyennv.lms.dto.course.CourseSectionUpdateRequest;
import com.quyennv.lms.entities.Section;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CourseSectionDtoMapper {

    @Mapping(target = "courseId", source = "courseId", qualifiedByName = "mapStringToUUID")
    Section toSection(CourseSectionCreateRequest request);

    @Mapping(target = "courseId", source = "courseId", qualifiedByName = "mapStringToUUID")
    Section toSectionUpdate(CourseSectionUpdateRequest request);

    @Named("mapStringToUUID")
    default UUID mapStringToUUID(String id) {
        return id == null ? null : UUID.fromString(id);
    }

}
