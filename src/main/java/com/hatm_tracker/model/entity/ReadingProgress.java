package com.hatm_tracker.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class ReadingProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    private Integer pageReadTo; // До какой страницы прочитал сегодня

    @ManyToOne
    @JoinColumn(name = "hatm_id")
    private Hatm hatm;
}
