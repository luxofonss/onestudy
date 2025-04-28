package com.quyennv.lms.dto.mappers;

import com.quyennv.lms.dto.course.CreateLessonRequest;
import com.quyennv.lms.dto.course.UpdateLessonRequest;
import com.quyennv.lms.entities.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LessonDtoMapper extends BaseMapper {

    @Mapping(target = "sectionId", source = "sectionId", qualifiedByName = "mapStringToUUID")
    @Mapping(target = "videoUrl", source = "videoUrl")
    Lesson toLesson(CreateLessonRequest request);

    @Mapping(target = "sectionId", source = "sectionId", qualifiedByName = "mapStringToUUID")
    Lesson toLessonUpdate(UpdateLessonRequest request);

}
