package com.quyennv.lms.dto.course;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.quyennv.lms.annotations.ValueOfEnum;
import com.quyennv.lms.constant.enums.LessonType;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateLessonRequest {

    @NotBlank
    String name;

    String description;

    @ValueOfEnum(enumClass = LessonType.class)
    String type;

    String resourceId;

    String sectionId;

    String courseId;

}
