package com.hatm_tracker.service.user_service;

import com.hatm_tracker.model.dto.HatmDto;
import com.hatm_tracker.model.dto.RegisterUserDto;
import com.hatm_tracker.model.dto.UserDto;
import com.hatm_tracker.model.dto.UserReqDto;
import com.hatm_tracker.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    RegisterUserDto getUserDto(UserDetails userDetails);

    RegisterUserDto deleteUser(UserDetails userDetails);

    RegisterUserDto updateUser(UserDetails userDetails, UserReqDto userResDto);

    User getUserByUsername(String username);
}
