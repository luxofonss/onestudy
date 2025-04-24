package com.quyennv.lms.entities;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Course {

    private UUID id;

    private String name;

    private String description;

    private String backgroundImg;

    private String thumbnail;

    private Date startDate;

    private Date endDate;

    private BigDecimal price;

    private String level;

    private Boolean isVerified;

    private UUID subjectId;

    private Integer grade;

    private String code;

    private UUID teacherId;

    private Date updatedAt;

    private Date createdAt;

    private Date deletedAt;

    private List<CourseInfo> courseInfos;

    private List<Section> sections;

}