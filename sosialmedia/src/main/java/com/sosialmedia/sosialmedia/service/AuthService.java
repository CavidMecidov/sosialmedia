package com.sosialmedia.sosialmedia.service;

import com.sosialmedia.sosialmedia.config.JwtService;
import com.sosialmedia.sosialmedia.config.PasswordEncoderConfig;
import com.sosialmedia.sosialmedia.dto.LoginResponse;
import com.sosialmedia.sosialmedia.dto.UserLoginRequest;
import com.sosialmedia.sosialmedia.dto.UserRegisterRequest;
import com.sosialmedia.sosialmedia.entity.User;
import com.sosialmedia.sosialmedia.enums.Role;
import com.sosialmedia.sosialmedia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginResponse register(UserRegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail()) ||
                userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Email və ya username artıq mövcuddur.");
        }

        User user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .enabled(true)
                .locked(false)
                .build();

        userRepository.save(user);

        String jwtToken = jwtService.generateAccessToken(user);
        return new LoginResponse(jwtToken);
    }

    public LoginResponse login(UserLoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication); // Authentication əlavə edin
        } catch (Exception e) {
            throw new RuntimeException("Login uğursuz oldu: " + e.getMessage());
        }


        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("İstifadəçi tapılmadı"));

        String jwtToken = jwtService.generateAccessToken(user);
        return new LoginResponse(jwtToken);
    }


}
