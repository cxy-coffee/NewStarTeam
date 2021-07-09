package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository  extends JpaRepository<Position,Long> {
}
