package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.repository.CompanyToReviewRepository;
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
    @Override
    public boolean deleteCompanyToReviewByCompanyId(int companyId) {
        companyToReviewRepository.deleteCompanyToReviewByCompanyId(companyId);
        return true;
    }
}
