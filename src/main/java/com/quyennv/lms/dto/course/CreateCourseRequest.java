package com.quyennv.lms.dto.course;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.quyennv.lms.annotations.ValueOfEnum;
import com.quyennv.lms.constant.enums.CourseInfoType;
import com.quyennv.lms.constant.enums.CourseLevel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateCourseRequest {

    @NotBlank
    String name;

    String description;

    String backgroundImage;

    String thumbnail;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    String startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    String endDate;

    BigDecimal price;

    @ValueOfEnum(enumClass = CourseLevel.class)
    String level;

    Integer grade;

    UUID subjectId;

    List<CreateCourseRequestCourseInfo> courseInfos;

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class CreateCourseRequestCourseInfo {
        UUID id;

        @NotBlank
        String content;

        @NotBlank
        @ValueOfEnum(enumClass = CourseInfoType.class)
        String type;
    }

}
