package com.quyennv.lms.dto.assignment;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.quyennv.lms.annotations.ValueOfEnum;
import com.quyennv.lms.constant.enums.QuestionLevel;
import com.quyennv.lms.constant.enums.QuestionType;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UpdateQuestionRequest {

    UUID assignmentId;

    String title;

    UUID questionId;

    String description;

    UUID imageResourceId;

    UUID audioResourceId;

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

        UUID questionChoiceId;

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

        UUID id;

        String answer;

        String explanation;

    }

}
