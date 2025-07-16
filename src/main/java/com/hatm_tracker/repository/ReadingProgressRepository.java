package com.hatm_tracker.repository;

import com.hatm_tracker.model.entity.ReadingProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingProgressRepository extends JpaRepository<ReadingProgress, Integer> {
}
