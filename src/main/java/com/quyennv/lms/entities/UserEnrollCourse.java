package com.quyennv.lms.entities;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEnrollCourse extends UserEnrollCourseKey {

    private UUID id;

    private BigDecimal price;

    private String enrollStatus;

    private Integer studentId;

    private Date updatedAt;

    private Date createdAt;

    private Date deletedAt;

    private User student;

}