package com.quyennv.lms.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Assignment {

    private UUID id;

    private String title;

    private String description;

    private Integer totalPoint;

    private Integer maxAttempts;

    private String type;

    private Date startTime;

    private Date endTime;

    private Integer timeLimitSecond;

    private List<Question> questions;

    private UUID lessonId;

    private UUID subjectId;

    private UUID teacherId;

    private Date deletedAt;

    private Date createdAt;

    private Date updatedAt;

}