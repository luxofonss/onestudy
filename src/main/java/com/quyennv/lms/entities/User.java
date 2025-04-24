package com.quyennv.lms.entities;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private UUID id;

    private String username;

    private String email;

    private Boolean isVerified;

    private String phoneNumber;

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String gender;

    private String role;

    private String avatar;

    private String address;

    private Auth auth;

    private Date deletedAt;

    private Date updatedAt;

    private Date createdAt;

}