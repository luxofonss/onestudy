package com.quyennv.lms.dto.assignment;

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
public class QuestionMutationRequest {

    UUID assignmentId;

    String title;

    UUID questionId;

    String description;

    UUID imageResourceId;

    String imageUrl;

    UUID audioResourceId;

    String audioUrl;

    Integer audioMaxPlayTime;

    Integer point;

    @ValueOfEnum(enumClass = QuestionLevel.class)
    String level;

    @ValueOfEnum(enumClass = QuestionType.class)
    String type;

    String v;

    List<QuestionChoice> choices;

    List<QuestionTextAnswer> textAnswers;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
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
    public static class QuestionTextAnswer {

        UUID id;

        String correctTextAnswer;

        String explanation;

    }

}
