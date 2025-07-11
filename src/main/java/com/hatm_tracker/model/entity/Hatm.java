package com.hatm_tracker.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Hatm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer hatmNumber; // номер хатыма
    private LocalDate startTime;
    private LocalDate endTime;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "hatm", cascade = CascadeType.ALL)
    List<ReadingProgress> progressList;
}
