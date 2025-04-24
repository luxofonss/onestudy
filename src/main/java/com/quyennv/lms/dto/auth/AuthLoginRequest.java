package com.quyennv.lms.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginRequest {

    @NotBlank
    String username;

    @NotBlank
    String password;

}
