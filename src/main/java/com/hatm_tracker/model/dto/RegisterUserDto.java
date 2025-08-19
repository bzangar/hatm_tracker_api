package com.hatm_tracker.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegisterUserDto {
    private String name;
    private String username;
    private String password;
}
