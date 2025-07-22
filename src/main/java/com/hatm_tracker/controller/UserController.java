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

    @GetMapping("/{id}")
    public UserDto getUserById(
            @PathVariable Integer id
    ){
        return userService.getUserDtoById(id);
    }

    @GetMapping()
    public List<UserDto> getAllUserDto(){
        return userService.getAllUserDto();
    }

    @PostMapping()
    public UserDto createUser(
            @RequestBody User user
    ){
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public UserReqDto deleteUserById(
            @PathVariable Integer id, @RequestBody UserReqDto userReqDto
            ){
        return userService.updateUserById(id, userReqDto);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUserById(@PathVariable Integer id){
        return userService.deleteUserById(id);
    }

    @GetMapping("/{id}/all-hatms")
    public List<HatmDto> getAllHatmDtoById(@PathVariable Integer id){
        return userService.getAllHatmDtoById(id);
    }
}
