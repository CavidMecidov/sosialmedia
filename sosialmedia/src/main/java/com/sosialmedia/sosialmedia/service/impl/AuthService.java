package com.sosialmedia.sosialmedia.service.impl;

import com.sosialmedia.sosialmedia.dto.LoginResponse;
import com.sosialmedia.sosialmedia.dto.UserLoginRequest;
import com.sosialmedia.sosialmedia.dto.UserRegisterRequest;
import com.sosialmedia.sosialmedia.entity.User;
import com.sosialmedia.sosialmedia.enums.Role;
import com.sosialmedia.sosialmedia.exception.ConflictException;
import com.sosialmedia.sosialmedia.exception.NotFoundException;
import com.sosialmedia.sosialmedia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
            throw new ConflictException("Email or username already exist.");
        }

        User user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .username(request.getUsername())
                .gender(request.getGender())
                .birthdate(request.getBirthdate())
                .phoneNumber(request.getPhoneNumber())
                .bio(request.getBio())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .enabled(true)
                .isActive(true)
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
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            throw new RuntimeException("Login failed:" + e.getMessage());
        }


        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new NotFoundException("User not found"));

        if(!user.isActive()){
            throw new BadCredentialsException("User deactivated");
        }

        String jwtToken = jwtService.generateAccessToken(user);
        return new LoginResponse(jwtToken);
    }


}
