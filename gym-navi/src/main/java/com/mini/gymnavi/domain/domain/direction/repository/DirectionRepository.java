package com.mini.gymnavi.domain.domain.direction.repository;

import com.mini.gymnavi.domain.domain.direction.domain.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
}
