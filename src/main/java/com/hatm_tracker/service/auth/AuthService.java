package com.hatm_tracker.service.auth;

import com.hatm_tracker.model.dto.AuthResponseDto;
import com.hatm_tracker.model.dto.LoginUserDto;
import com.hatm_tracker.model.dto.RegisterUserDto;

public interface AuthService {
    AuthResponseDto register (RegisterUserDto registerUserDto);

    AuthResponseDto login(LoginUserDto loginUserDto);
}
