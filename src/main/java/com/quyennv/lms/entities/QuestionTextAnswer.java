package com.quyennv.lms.entities;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class QuestionTextAnswer {

    private UUID id;

    private UUID questionId;

    private String correctTextAnswer;

    private String explanation;

    private Date deletedAt;

    private Date createdAt;

    private Date updatedAt;
}