package com.rookiestar.starmanager.util;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.repository.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
    @Test
    public void testInit() throws Exception{
        DataBaseUtil.getInstance().initCompany(companyRepository);
        DataBaseUtil.getInstance().initDepartment(departmentRepository);
        DataBaseUtil.getInstance().initPosition(positionRepository);
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        DataBaseUtil.getInstance().initAssessment(assessmentRepository);
    }

}