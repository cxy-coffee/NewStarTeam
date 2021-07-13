package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.entity.position.Position;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class that access to the table position
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
public interface PositionRepository  extends JpaRepository<Position,Long> {
}
