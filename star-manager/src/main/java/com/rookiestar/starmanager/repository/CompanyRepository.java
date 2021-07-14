package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.entity.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository class that access to the table company
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
public interface CompanyRepository extends JpaRepository<Company,Long> {
    /**
     * get the biggest companyId by now
     * @return the value of the biggest companyId by now
     */
    @Query("select max(company.companyId) from Company company")
    int findMaxCompanyId();

    /**
     * get a company by its companyId
     * @param companyId the companyId of the company to find
     * @return the company whose comapnyId matches the param
     */
    Company findByCompanyId(int companyId);
}
