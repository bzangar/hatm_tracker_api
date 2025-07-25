package com.hatm_tracker.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserReqDto {
    private Integer id;
    private String name;
    private String username;
    private String password;
}
