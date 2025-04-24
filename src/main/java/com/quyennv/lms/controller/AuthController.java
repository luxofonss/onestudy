package com.quyennv.lms.controller;

import com.quyennv.lms.annotations.CurrentUser;
import com.quyennv.lms.annotations.LogsActivityAnnotation;
import com.quyennv.lms.dto.BaseResponse;
import com.quyennv.lms.dto.auth.*;
import com.quyennv.lms.security.UserPrincipal;
import com.quyennv.lms.service.AuthService;
import com.quyennv.lms.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {

    private final BaseService baseService;

    private final AuthService authService;

    public AuthController(BaseService baseService, AuthService authService) {
        this.baseService = baseService;
        this.authService = authService;
    }

    @PostMapping("/login")
    @LogsActivityAnnotation
    public BaseResponse<AuthLoginResponse> login(@Valid @RequestBody AuthLoginRequest request) {
        return baseService.ofSucceeded(authService.login(request));
    }

    @PostMapping("/register")
    public BaseResponse<AuthRegisterResponse> register(@Valid @RequestBody AuthRegisterRequest request) {
        return baseService.ofSucceeded(authService.register(request));
    }

    @GetMapping("/whoami")
    public BaseResponse<AuthProfileResponse> whoAmI(@CurrentUser UserPrincipal requester) {
        return baseService.ofSucceeded(authService.getAuthProfile(requester.getId().toString()));
    }
}
