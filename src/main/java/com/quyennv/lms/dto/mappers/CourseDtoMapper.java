package com.quyennv.lms.dto.mappers;

import com.quyennv.lms.dto.course.CreateCourseRequest;
import com.quyennv.lms.dto.course.CreateCourseResponse;
import com.quyennv.lms.dto.course.UpdateCourseRequest;
import com.quyennv.lms.entities.Course;
import com.quyennv.lms.entities.CourseInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CourseDtoMapper {

    @Mapping(source = "startDate", target = "startDate", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "endDate", target = "endDate", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "backgroundImage", target = "backgroundImg")
    Course toCourse(CreateCourseRequest request);

    @Mapping(source = "startDate", target = "startDate", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "endDate", target = "endDate", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "backgroundImage", target = "backgroundImg")
    Course updateRequestToCourse(UpdateCourseRequest request);

    CreateCourseResponse toCreateCourseResponse(Course course);

    @Mapping(target = "id", source = "id", qualifiedByName = "mapStringToUUID")
    CourseInfo toCourseInfo(CreateCourseRequest.CreateCourseRequestCourseInfo info);

    @Named("mapStringToUUID")
    default UUID mapStringToUUID(String id) {
        return id == null ? null : UUID.fromString(id);
    }

}
