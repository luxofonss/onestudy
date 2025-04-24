package com.quyennv.lms.dto.assignment;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.quyennv.lms.annotations.ValueOfEnum;
import com.quyennv.lms.constant.enums.QuestionLevel;
import com.quyennv.lms.constant.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateQuestionRequest {

    UUID assignmentId;

    String title;

    String questionId;

    String description;

    String imageResourceId;

    String audioResourceId;

    Integer audioMaxPlayTime;

    Integer point;

    @ValueOfEnum(enumClass = QuestionLevel.class)
    String level;

    @ValueOfEnum(enumClass = QuestionType.class)
    String type;

    String answerExplain;

    List<QuestionChoice> choices;

    List<QuestionTextAnswer> textAnswers;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class QuestionChoice {
        @NotBlank
        String content;

        Integer order;

        Boolean isCorrect;

        String explanation;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class QuestionTextAnswer {

        String answer;

        String explanation;

    }

}
