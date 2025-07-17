package com.hatm_tracker.model.dto;

import com.hatm_tracker.model.entity.Hatm;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class ReadingProgressDto {
    private Integer id;
    private LocalDate date;
    private Integer pageReadTo; // До какой страницы прочитал сегодня
    private HatmDto hatm;
}
