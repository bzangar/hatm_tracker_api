package com.hatm_tracker.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hatm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer hatmNumber; // номер хатыма
    private LocalDate startTime;
    private LocalDate endTime;
    private boolean isEnd;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "hatm", cascade = CascadeType.ALL)
    List<ReadingProgress> progressList;
}
