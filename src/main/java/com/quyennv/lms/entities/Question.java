package com.quyennv.lms.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    private UUID id;

    private UUID questionId;

    private UUID assignmentId;

    private String title;

    private UUID imageResourceId;

    private String imageUrl;

    private UUID audioResourceId;

    private String audioUrl;

    private Integer audioMaxPlayTime;

    private Integer point;

    private String type;

    private String level;

    private String answerExplain;

    private UUID teacherId;

    private UUID subjectId;

    private Date deletedAt;

    private Date createdAt;

    private Date updatedAt;

    private List<QuestionChoice> choices;

    private List<QuestionTextAnswer> textAnswers;

}