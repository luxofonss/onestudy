package com.quyennv.lms.entities;

import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Section {

    private UUID id;

    private String name;

    private String description;

    private UUID courseId;

    private Date deletedAt;

    private Date createdAt;

    private Date updatedAt;

    private List<Lesson> lessons;

}