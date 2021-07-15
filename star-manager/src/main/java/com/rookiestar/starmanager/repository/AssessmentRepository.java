package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.entity.assessment.Assessment;
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
     * 通过员工账号，公司Id，入职时间查找员工评价
     * @param accountNumber 员工账号
     * @param companyId 公司Id
     * @param startTime 入职时间
     * @return Assessment
     */
    Assessment findByAccountNumberAndCompanyIdAndStartTime(int accountNumber, int companyId, Date startTime);
}
