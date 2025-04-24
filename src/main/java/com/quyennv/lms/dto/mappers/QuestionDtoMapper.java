package com.quyennv.lms.dto.mappers;

import com.quyennv.lms.dto.assignment.CreateQuestionRequest;
import com.quyennv.lms.dto.assignment.UpdateQuestionRequest;
import com.quyennv.lms.entities.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionDtoMapper {

    Question toQuestion(CreateQuestionRequest request);

    Question toQuestion(UpdateQuestionRequest request);

}
