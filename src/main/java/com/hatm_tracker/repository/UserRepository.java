package com.hatm_tracker.repository;

import com.hatm_tracker.model.entity.Hatm;
import com.hatm_tracker.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

}
