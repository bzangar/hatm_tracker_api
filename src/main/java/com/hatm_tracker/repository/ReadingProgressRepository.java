package com.hatm_tracker.repository;

import com.hatm_tracker.model.entity.Hatm;
import com.hatm_tracker.model.entity.ReadingProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReadingProgressRepository extends JpaRepository<ReadingProgress, Integer> {
    List<ReadingProgress> findAllByHatmId(Integer id);

    Optional<ReadingProgress> findTopByHatmOrderByDateTimeDesc(Hatm hatm);
}
