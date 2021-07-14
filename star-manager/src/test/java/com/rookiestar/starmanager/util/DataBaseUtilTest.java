package com.rookiestar.starmanager.util;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.repository.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Test class that test DataBaseUtil
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
public class DataBaseUtilTest extends BaseTest {
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
    @Test
    public void testInit() throws Exception{
        DataBaseUtil.getInstance().initCompany(companyRepository);
        DataBaseUtil.getInstance().initDepartment(departmentRepository);
        DataBaseUtil.getInstance().initPosition(positionRepository);
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        DataBaseUtil.getInstance().initAssessment(assessmentRepository);
        DataBaseUtil.getInstance().initCompanyManager(companyManagerRepository);
        DataBaseUtil.getInstance().initManager(managerRepository);
    }

}