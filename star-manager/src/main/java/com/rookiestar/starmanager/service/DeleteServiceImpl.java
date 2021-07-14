package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.repository.CompanyToReviewRepository;
import com.rookiestar.starmanager.repository.DepartmentRepository;
import com.rookiestar.starmanager.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chris
 * @date 2021/7/13
 * @time 16:36
 */
@Service
public class DeleteServiceImpl implements DeleteService{
    @Autowired
    private CompanyToReviewRepository companyToReviewRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public boolean deleteCompanyToReviewByCompanyId(int companyId) {
        companyToReviewRepository.deleteCompanyToReviewByCompanyId(companyId);
        return true;
    }

    @Override
    public boolean deletePositionByCompanyIdAndDepartmentIdAndPositionId(int companyId, int departmentId, int positionId) {
        positionRepository.deletePositionByCompanyIdAndDepartmentIdAndPositionId(companyId,departmentId,positionId);
        return true;
    }

    @Override
    public boolean deleteDepartmentByCompanyIdAndDepartmentId(int companyId, int departmentId) {
        departmentRepository.deleteDepartmentByCompanyIdAndDepartmentId(companyId,departmentId);
        return true;
    }
}
