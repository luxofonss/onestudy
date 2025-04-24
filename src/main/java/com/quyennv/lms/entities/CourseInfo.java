package com.quyennv.lms.entities;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class CourseInfo {
    private UUID id;

    private UUID courseId;

    private String content;

    private String type;

    private Date deletedAt;

    private Date createdAt;

    private Date updatedAt;

}