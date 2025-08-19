package com.hatm_tracker.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ReadingProgressDto {
    private Integer id;
    private LocalDateTime dateTime;
    private Integer pageReadTo; // До какой страницы прочитал сегодня
    private HatmDto hatm;
}
