package com.quyennv.lms.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionTextAnswer {

    private UUID id;

    private UUID questionId;

    private String correctTextAnswer;

    private String explanation;

    private Date deletedAt;

    private Date createdAt;

    private Date updatedAt;
}