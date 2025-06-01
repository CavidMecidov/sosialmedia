package com.sosialmedia.sosialmedia.controller;

import com.sosialmedia.sosialmedia.service.impl.AuthService;
import com.sosialmedia.sosialmedia.dto.BaseResponse;
import com.sosialmedia.sosialmedia.dto.LoginResponse;
import com.sosialmedia.sosialmedia.dto.UserLoginRequest;
import com.sosialmedia.sosialmedia.dto.UserRegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public BaseResponse<LoginResponse> register(@Valid @RequestBody UserRegisterRequest request) {
        var loginResponse = authService.register(request);
        BaseResponse<LoginResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(loginResponse);
        baseResponse.setMessage("Register successful");
        baseResponse.setSuccess(true);
        return baseResponse;
    }

    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@Valid @RequestBody UserLoginRequest loginRequest) {
        var loginResponse = authService.login(loginRequest);
        BaseResponse<LoginResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(loginResponse);
        baseResponse.setMessage("Login successful");
        baseResponse.setSuccess(true);
        return baseResponse;
    }
}
