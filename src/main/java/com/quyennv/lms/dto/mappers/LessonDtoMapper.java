package com.quyennv.lms.dto.mappers;

import com.quyennv.lms.dto.course.CreateLessonRequest;
import com.quyennv.lms.entities.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LessonDtoMapper extends BaseMapper {

    @Mapping(target = "sectionId", source = "sectionId", qualifiedByName = "mapStringToUUID")
    Lesson toLesson(CreateLessonRequest request);

}
