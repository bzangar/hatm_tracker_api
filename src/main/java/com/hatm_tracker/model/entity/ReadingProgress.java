package com.hatm_tracker.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadingProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime dateTime;
    private Integer pageReadTo; // До какой страницы прочитал сегодня

    @ManyToOne
    @JoinColumn(name = "hatm_id")
    private Hatm hatm;
}
