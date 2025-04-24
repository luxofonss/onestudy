package com.quyennv.lms.dto.course;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.quyennv.lms.annotations.ValueOfEnum;
import com.quyennv.lms.constant.enums.CourseInfoType;
import com.quyennv.lms.constant.enums.LessonType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UpdateCourseRequest {

    String name;

    String description;

    String backgroundImg;

    String thumbnail;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    String startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    String endDate;

    BigDecimal price;

    String level;

    Integer grade;

    String subjectId;

    List<UpdateCourseRequestCourseInfo> courseInfos;

    List<UpdateCourseRequestSection> sections;

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class UpdateCourseRequestCourseInfo {
        UUID id;

        @NotBlank
        String content;

        String type;
    }

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class UpdateCourseRequestSection {
        UUID id;

        @NotBlank
        String name;

        String description;

        List<UpdateCourseRequestSectionLesson> lessons;
    }

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class UpdateCourseRequestSectionLesson {
        UUID id;

        @NotBlank
        String name;

        String description;

        @ValueOfEnum(enumClass = LessonType.class)
        String type;

        String resourceId;
    }

}
