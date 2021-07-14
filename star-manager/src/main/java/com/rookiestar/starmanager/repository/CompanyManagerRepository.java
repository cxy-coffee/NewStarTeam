package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.entity.companymanager.CompanyManager;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class that access to the table companyManager
 *
 * @author 曹向阳
 * @date 2021/7/12
 */
public interface CompanyManagerRepository extends JpaRepository<CompanyManager,Long> {
    /**
     * find companyManager by companyId,email,jobNumber
     *
     * @param companyId the companyId of a company
     * @param email the email of a CompanyManager
     * @param jobNumber the jobNumber of a CompanyManager
     * @return CompanyManager
     */
    CompanyManager findByCompanyIdAndEmailAndJobNumber(int companyId, String email, int jobNumber);
}
