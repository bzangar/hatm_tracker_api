package com.hatm_tracker.controller;


import com.hatm_tracker.model.dto.AuthResponseDto;
import com.hatm_tracker.model.dto.LoginUserDto;
import com.hatm_tracker.model.dto.RegisterUserDto;
import com.hatm_tracker.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponseDto register(@RequestBody RegisterUserDto registerUserDto){

        return authService.register(registerUserDto);
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody LoginUserDto loginUserDto){

        return authService.login(loginUserDto);
    }
}
