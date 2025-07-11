package com.hatm_tracker.service;

import com.hatm_tracker.exception.UserNotFoundException;
import com.hatm_tracker.model.Mapper;
import com.hatm_tracker.model.dto.UserDto;
import com.hatm_tracker.model.entity.User;
import com.hatm_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    final private UserRepository userRepository;
    final private Mapper mapper;


    @Override
    public UserDto getUserById(Integer id) {
        User user =  userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!!!"));

        return mapper.userFromEntityToDto(user);
    }

    @Override
    public UserDto createUser(User user) {
        userRepository.save(user);
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .build();
    }
}
