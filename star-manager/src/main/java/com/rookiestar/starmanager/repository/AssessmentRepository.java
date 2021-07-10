package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.entity.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repository class that access to the table assessment
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
public interface AssessmentRepository extends JpaRepository<Assessment,Long> {
}
