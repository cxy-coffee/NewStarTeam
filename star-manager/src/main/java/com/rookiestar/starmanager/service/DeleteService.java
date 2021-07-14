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

    /**
     * delete a position by its primary key
     * @param companyId the companyId of the position to delete
     * @param departmentId the departmentId of the position to delete
     * @param positionId the positionId of the position to delete
     * @return the result of this delete operation. true for success and false for non-success
     */
    boolean deletePositionByCompanyIdAndDepartmentIdAndPositionId(int companyId, int departmentId, int positionId);

    /**
     * delete a department by its primary key
     * @param companyId the companyId of the department to delete
     * @param departmentId the departmentId of the department to delete
     * @return the result of this delete operation. true for success and false for non-success
     */
    boolean deleteDepartmentByCompanyIdAndDepartmentId(int companyId, int departmentId);
}
