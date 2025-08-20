package com.hatm_tracker.controller;


import com.hatm_tracker.model.dto.HatmDto;
import com.hatm_tracker.model.dto.RegisterUserDto;
import com.hatm_tracker.model.dto.UserDto;
import com.hatm_tracker.model.dto.UserReqDto;
import com.hatm_tracker.service.user_service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/hatm-tracker/users/me")
@RequiredArgsConstructor
public class UserController {

    final private UserService userService;

    @GetMapping()
    public RegisterUserDto getUserById(
            @AuthenticationPrincipal UserDetails userDetails
    ){

        return userService.getUserDto(userDetails);
    }

    @PutMapping()
    public RegisterUserDto deleteUserById(
            @AuthenticationPrincipal UserDetails userDetails, @RequestBody UserReqDto userReqDto
            ){

        return userService.updateUser(userDetails, userReqDto);
    }

    @DeleteMapping()
    public RegisterUserDto deleteUserById(@AuthenticationPrincipal UserDetails userDetails){

        return userService.deleteUser(userDetails);
    }

}
