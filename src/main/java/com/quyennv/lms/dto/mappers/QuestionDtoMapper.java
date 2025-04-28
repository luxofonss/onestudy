package com.quyennv.lms.dto.mappers;

import com.quyennv.lms.dto.assignment.QuestionMutationRequest;
import com.quyennv.lms.entities.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestionDtoMapper {

    @Mapping(target = "imageUrl" , source = "imageUrl")
    Question toQuestion(QuestionMutationRequest request);

}
