package com.hatm_tracker.service;

import com.hatm_tracker.model.dto.UserDto;
import com.hatm_tracker.model.entity.User;

public interface UserService {
    UserDto getUserById(Integer id);

    UserDto createUser(User user);
}
