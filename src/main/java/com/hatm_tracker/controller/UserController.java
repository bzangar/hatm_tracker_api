package com.hatm_tracker.controller;


import com.hatm_tracker.model.dto.HatmDto;
import com.hatm_tracker.model.dto.UserDto;
import com.hatm_tracker.model.dto.UserReqDto;
import com.hatm_tracker.model.entity.User;
import com.hatm_tracker.service.user_service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/hatm-tracker/users")
@RequiredArgsConstructor
public class UserController {

    final private UserService userService;

    @GetMapping("/get/{id}")
    public UserDto getUserById(
            @PathVariable Integer id
    ){
        return userService.getUserDtoById(id);
    }

    @PostMapping("/create")
    public UserDto createUser(
            @RequestBody User user
    ){
        return userService.createUser(user);
    }

    @GetMapping("/get/all")
    public List<UserDto> getAllUserDto(){
        return userService.getAllUserDto();
    }

    @PutMapping("/update/{id}")
    public UserReqDto deleteUserById(
            @PathVariable Integer id, @RequestBody UserReqDto userReqDto
            ){
        return userService.updateUserById(id, userReqDto);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteUserById(@PathVariable Integer id){
        return userService.deleteUserById(id);
    }

    @GetMapping("/get/all-hatms/{id}")
    public List<HatmDto> getAllHatmDtoById(@PathVariable Integer id){
        return userService.getAllHatmDtoById(id);
    }
}
