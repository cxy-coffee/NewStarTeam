package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
