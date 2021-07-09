package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience,Long> {
    List<Experience> findAllByAccountNumber(int accountNumber);
    Experience findByAccountNumberAndCompanyIdAndDepartmentIdAndPositionId(int accountNumber,int companyId,int departmentId,int positionId);
}
