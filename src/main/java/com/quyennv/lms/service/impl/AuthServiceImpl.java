package com.quyennv.lms.service.impl;

import com.quyennv.lms.dto.mappers.UserDtoMapper;
import com.quyennv.lms.dto.auth.AuthLoginRequest;
import com.quyennv.lms.dto.auth.AuthRegisterRequest;
import com.quyennv.lms.dto.auth.AuthLoginResponse;
import com.quyennv.lms.dto.auth.AuthProfileResponse;
import com.quyennv.lms.dto.auth.AuthRegisterResponse;
import com.quyennv.lms.entities.Auth;
import com.quyennv.lms.entities.User;
import com.quyennv.lms.repository.UserRepository;
import com.quyennv.lms.security.JwtProvider;
import com.quyennv.lms.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserDtoMapper userDtoMapper;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           JwtProvider jwtProvider,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           UserDtoMapper userDtoMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDtoMapper = userDtoMapper;
    }

    @Override
    public AuthRegisterResponse register(AuthRegisterRequest request) {
        User user = userRepository.getByUsername(request.getUsername()).orElseThrow(() ->  new RuntimeException("user exists"));

        User userData = userDtoMapper.authRegisterRequestToUser(request);
        Auth auth = Auth
                .builder()
                .password(passwordEncoder.encode(request.getPassword()))
                .userId(userData.getId())
                .build();
        userData.setAuth(auth);
        userRepository.persist(userData);
        return new AuthRegisterResponse(userData.getId().toString());
    }

    @Override
    public AuthProfileResponse getAuthProfile(String id) {
        User user = userRepository.getUserById(id).orElseThrow(() -> new RuntimeException("User not found"));

        return userDtoMapper.userToAuthProfileResponse(user);
    }

    @Override
    public AuthLoginResponse login(AuthLoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        return new AuthLoginResponse(
                jwtProvider.generateAccessToken(authentication),
                jwtProvider.generateRefreshToken(authentication));
    }

}
