package com.hatm_tracker.model.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class HatmDto {
    private Integer id;
    private String name;
    private Integer hatmNumber;
    private LocalDate startTime;
    private LocalDate endTime;
    private boolean isEnd;
    private UserDto user;
}
