
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
public class QuestionChoice {

    private UUID id;

    private String content;

    private Integer order;

    private Boolean isCorrect;

    private String explanation;

    private UUID questionId;

    private Date deletedAt;

    private Date createdAt;

    private Date updatedAt;

}