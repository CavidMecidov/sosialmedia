package com.sosialmedia.sosialmedia.controller;

import com.sosialmedia.sosialmedia.dto.LoginResponse;
import com.sosialmedia.sosialmedia.dto.UserLoginRequest;
import com.sosialmedia.sosialmedia.dto.UserRegisterRequest;
import com.sosialmedia.sosialmedia.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<LoginResponse> register(@RequestBody UserRegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserLoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
