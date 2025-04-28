package com.quyennv.lms.entities;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Lesson {

    private UUID id;

    private String name;

    private String description;

    private String type;

    private UUID resourceId;

    private UUID sectionId;

    private UUID assignmentId;

    private String videoUrl;

    private Date updatedAt;

    private Date createdAt;

    private Date deletedAt;

}