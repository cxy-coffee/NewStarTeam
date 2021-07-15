package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.entity.company.CompanyToReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author chris
 * @date 2021/7/13
 * @time 16:09
 */
public interface CompanyToReviewRepository extends JpaRepository<CompanyToReview,Long> {

    /**
     * delete a CompanyToReview object in the database having the exact companyId
     * @param companyId the companyId of the CompanyToReview object to delete
     */
    void deleteCompanyToReviewByCompanyId(int companyId);

    /**
     * find the biggest Id of all companyToReview objects in the database
     * @return the biggest Id of all companyToReview objects in the database
     */
    @Query("select max(companyToReview.companyId) from CompanyToReview companyToReview")
    int findMaxCompanyToReviewId();

    /**
     * find a CompanyToReview object by companyId
     *
     * @param companyId the companyId of the CompanyToReview object
     * @return CompanyToReview
     */
    CompanyToReview findByCompanyId(int companyId);
}
