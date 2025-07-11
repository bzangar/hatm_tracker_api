package com.hatm_tracker.controller;


import com.hatm_tracker.model.dto.UserDto;
import com.hatm_tracker.model.entity.User;
import com.hatm_tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/hatm/users")
@RequiredArgsConstructor
public class HatmController {

    final private UserService userService;

    @GetMapping("/get/{id}")
    public UserDto getUserById(
            @PathVariable Integer id
    ){
        return userService.getUserById(id);
    }

    @PostMapping("/create")
    public UserDto createUser(
            @RequestBody User user
    ){
        return userService.createUser(user);
    }
}
