package com.hatm_tracker.model;

import com.hatm_tracker.model.dto.HatmDto;
import com.hatm_tracker.model.dto.UserDto;
import com.hatm_tracker.model.dto.UserReqDto;
import com.hatm_tracker.model.entity.Hatm;
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

    public UserReqDto userFromEntityToDto_Req(User user){
        return UserReqDto.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    public HatmDto hatmFromEntityToDto(Hatm hatm){
        return HatmDto.builder()
                .id(hatm.getId())
                .name(hatm.getName())
                .startTime(hatm.getStartTime())
                .endTime(hatm.getEndTime())
                .hatmNumber(hatm.getHatmNumber())
                .build();
    }
}
