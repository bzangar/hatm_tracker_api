package com.hatm_tracker.repository;

import com.hatm_tracker.model.entity.Hatm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HatmRepository extends JpaRepository<Hatm, Integer> {
    int countAllByOrderByHatmNumberAsc();

    List<Hatm> findByHatmNumberGreaterThanOrderByHatmNumberAsc(Integer deletedHatmNumber);
}
