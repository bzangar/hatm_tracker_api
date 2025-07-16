package com.hatm_tracker.service;

import com.hatm_tracker.model.dto.UserDto;
import com.hatm_tracker.model.dto.UserReqDto;
import com.hatm_tracker.model.entity.User;

import java.util.List;

public interface UserService {
    UserDto getUserDtoById(Integer id);

    List<UserDto> getAllUserDto();

    UserDto createUser(User user);

    boolean deleteUserById(Integer id);

    UserReqDto updateUserById(Integer id, UserReqDto userResDto);

    User getUserById(Integer id);
}
