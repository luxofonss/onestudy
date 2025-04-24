package com.quyennv.lms.service;

import com.quyennv.lms.dto.auth.AuthLoginRequest;
import com.quyennv.lms.dto.auth.AuthRegisterRequest;
import com.quyennv.lms.dto.auth.AuthLoginResponse;
import com.quyennv.lms.dto.auth.AuthProfileResponse;
import com.quyennv.lms.dto.auth.AuthRegisterResponse;

public interface AuthService {

    AuthLoginResponse login(AuthLoginRequest request);

    AuthRegisterResponse register(AuthRegisterRequest request);

    AuthProfileResponse getAuthProfile(String email);

}
