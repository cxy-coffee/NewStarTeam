package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.entity.experience.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
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

    /**
     * find an experience by its primary key
     * @param accountNumber the accountNumber of the experience to find
     * @param companyId the companyNumber of the experience to find
     * @param startTime the startTime of the experience to find
     * @return the experience which matches all the params
     */
    Experience findByAccountNumberAndCompanyIdAndStartTime(int accountNumber, int companyId, Date startTime);

    /**
     * find all the experiences of a company
     * @param companyId the companyId of the experiences to find
     * @return all the experiences which match all the params
     */
    List<Experience> findByCompanyId(int companyId);
}
