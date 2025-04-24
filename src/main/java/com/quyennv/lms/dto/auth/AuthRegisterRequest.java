package com.quyennv.lms.dto.auth;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.quyennv.lms.annotations.ValueOfEnum;
import com.quyennv.lms.constant.Gender;
import com.quyennv.lms.constant.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AuthRegisterRequest {

    @NotBlank(message = "Username is required")
    String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    String email;

    @NotBlank(message = "Password is required")
    String password;

    @NotBlank(message = "First name is required")
    String firstName;

    @NotBlank(message = "Last name is required")
    String lastName;

    @NotBlank(message = "Phone number is required")
    String phoneNumber;

    @NotNull(message = "gender is required")
    @ValueOfEnum(enumClass = Gender.class, message = "Gender is not valid")
    String gender;

    @NotBlank(message = "Date of birth is required")
    String dateOfBirth;

    String avatar;

    String authType;

    @ValueOfEnum(enumClass = Role.class, message = "Role is not valid")
    String role;
//
//    LearnerInfo learnerInfo;
//
//    TeacherInfo teacherInfo;

}
