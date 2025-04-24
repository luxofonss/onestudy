package com.quyennv.lms.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class Auth {

    private Object userId;

    private String salt;

    private String password;

    private Date updatedAt;

    private Date createdAt;

    private Date deletedAt;

}