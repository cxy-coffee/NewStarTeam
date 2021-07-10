package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository class that access to the table experience
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
public interface ExperienceRepository extends JpaRepository<Experience,Long> {
    /**
     * find all experiences of an employee with the accountNumber
     *
     * @param accountNumber accountNumber
     * @return List<Experience>
     */
    List<Experience> findAllByAccountNumber(int accountNumber);

    /**
     * find an experience by accountNumber,companyId,departmentId,positionId
     *
     * @param accountNumber accountNumber
     * @param companyId companyId
     * @param departmentId departmentId
     * @param positionId positionId
     * @return Experience
     */
    Experience findByAccountNumberAndCompanyIdAndDepartmentIdAndPositionId(int accountNumber,int companyId,int departmentId,int positionId);
}
