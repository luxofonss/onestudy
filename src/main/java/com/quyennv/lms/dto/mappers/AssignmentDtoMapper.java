package com.quyennv.lms.dto.mappers;

import com.quyennv.lms.dto.assignment.CreateAssignmentRequest;
import com.quyennv.lms.dto.assignment.UpdateAssignmentRequest;
import com.quyennv.lms.entities.Assignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AssignmentDtoMapper {

    @Mapping(source = "startTime", target = "startTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "endTime", target = "endTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    Assignment toAssignment(CreateAssignmentRequest request);

    @Mapping(source = "startTime", target = "startTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "endTime", target = "endTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    Assignment toAssignment(UpdateAssignmentRequest request);

}
