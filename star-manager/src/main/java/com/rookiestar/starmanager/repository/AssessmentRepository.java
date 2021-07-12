package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.entity.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * Repository class that access to the table assessment
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
public interface AssessmentRepository extends JpaRepository<Assessment,Long> {
    /**
     * Get assessment by its primary key
     * @param accountNumber the account number of the assessment to find
     * @param companyId the company id of the assessment to find
     * @param startTime the start time of the assessment to find
     * @return the assessment having all params matched
     */
    Assessment findByAccountNumberAndCompanyIdAndStartTime(int accountNumber, int companyId, Date startTime);
}
