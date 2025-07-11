package com.hatm_tracker.model;

import com.hatm_tracker.model.dto.UserDto;
import com.hatm_tracker.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public UserDto userFromEntityToDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .build();
    }
}
