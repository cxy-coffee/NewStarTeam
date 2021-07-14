package com.rookiestar.starmanager.service;

/**
 * @author chris
 * @date 2021/7/13
 * @time 16:35
 */
public interface DeleteService {
    /**
     * delete a CompanyToReview object in the database by the companyId
     * @param companyId the companyId of the CompanyToReview object
     * @return the result of this delete operation. true for success and false for non-success
     */
    boolean deleteCompanyToReviewByCompanyId(int companyId);
}
