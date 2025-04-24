package com.quyennv.lms.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
public class Users {

    private UUID id;

    private Date createdAt;

    private Date deletedAt;

    private Date updatedAt;

    private String avatar;

    private Date dob;

    private String email;

    private String firstName;

    private String gender;

    private Boolean verified;

    private String lastName;

    private String phoneNumber;

    private String role;

    private String username;

    private String address;

    private String password;

}