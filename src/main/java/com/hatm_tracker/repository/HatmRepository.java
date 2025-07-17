package com.hatm_tracker.repository;

import com.hatm_tracker.model.entity.Hatm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HatmRepository extends JpaRepository<Hatm, Integer> {
    int countAllByOrderByHatmNumberAsc();

    List<Hatm> findByHatmNumberGreaterThanOrderByHatmNumberAsc(Integer deletedHatmNumber);

    List<Hatm> findAllByUserId(Integer id);

    Optional<Hatm> findTopByOrderByHatmNumberDesc();
}
