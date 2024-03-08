package com.mini.gymnavi.domain.domain.gym.repository;

import com.mini.gymnavi.domain.domain.gym.domain.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym, Long> {
}
