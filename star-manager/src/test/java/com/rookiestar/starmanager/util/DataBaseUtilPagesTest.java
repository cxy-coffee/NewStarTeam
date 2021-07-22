package com.rookiestar.starmanager.util;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.repository.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Description
 *
 * @author 曹向阳
 * @date 2021/7/20
 */
public class DataBaseUtilPagesTest extends BaseTest {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ExperienceRepository experienceRepository;
    @Autowired
    private AssessmentRepository assessmentRepository;
    @Autowired
    private CompanyManagerRepository companyManagerRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private CompanyToReviewRepository companyToReviewRepository;
    @Autowired
    private JobHuntingRepository jobHuntingRepository;
    @Test
    public void testInit() throws Exception{
        DataBaseUtilPages.getInstance().initCompany(companyRepository);
        DataBaseUtilPages.getInstance().initDepartment(departmentRepository);
        DataBaseUtilPages.getInstance().initPosition(positionRepository);
        DataBaseUtilPages.getInstance().initEmployee(employeeRepository);
        DataBaseUtilPages.getInstance().initExperience(experienceRepository);
        DataBaseUtilPages.getInstance().initAssessment(assessmentRepository);
        DataBaseUtilPages.getInstance().initCompanyManager(companyManagerRepository);
        DataBaseUtilPages.getInstance().initManager(managerRepository);
        DataBaseUtilPages.getInstance().initCompanyToReview(companyToReviewRepository);
        DataBaseUtilPages.getInstance().initJobHunting(jobHuntingRepository);
    }
}