package com.hatm_tracker.model.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class HatmDto {
    private Integer id;
    private String name;
    private Integer hatmNumber;
    private LocalDate startTime;
    private LocalDate endTime;
    private UserDto user;
}
