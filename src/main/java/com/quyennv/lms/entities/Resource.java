package com.quyennv.lms.entities;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class Resource {

    private UUID id;

    private String title;

    private String url;

    private String description;

    private UUID ownerId;

    private Date updatedAt;

    private Date createdAt;

    private Date deletedAt;

}