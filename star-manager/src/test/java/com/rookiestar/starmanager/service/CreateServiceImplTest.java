package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.assessment.Assessment;
import com.rookiestar.starmanager.entity.employee.Employee;
import com.rookiestar.starmanager.entity.experience.Experience;
import com.rookiestar.starmanager.repository.AssessmentRepository;
import com.rookiestar.starmanager.repository.EmployeeRepository;
import com.rookiestar.starmanager.repository.ExperienceRepository;
import com.rookiestar.starmanager.util.DataBaseUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Test class that test CreateServiceImpl
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
public class CreateServiceImplTest extends BaseTest {
    @Autowired
    private CreateService createService;
    @Autowired
    private ExperienceRepository experienceRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AssessmentRepository assessmentRepository;

    @Test
    @Transactional
    public void hireEmployeeTest() throws Exception{
        Experience experience = new Experience();
        experience.setAccountNumber(9);
        experience.setCompanyId(1);
        experience.setDepartmentId(1);
        experience.setPositionId(1);

        Experience expectExperience = createService.hireEmployee(experience);

        Experience addedExperience = experienceRepository.findByAccountNumberAndCompanyIdAndDepartmentIdAndPositionId(9,1,1,1);

        Assert.assertEquals(expectExperience,addedExperience);

        Assessment addedAssessment = assessmentRepository.findByAccountNumberAndCompanyIdAndStartTime(9,1,expectExperience.getStartTime());

        Assessment expectAssessment = new Assessment(9,1,expectExperience.getStartTime(),"","");

        Assert.assertEquals(expectAssessment,addedAssessment);
    }

    @Test
    @Transactional
    public void registerEmployeeTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);

        Employee employee = new Employee("测试名字",new Date(),"男","199","991",0,"123",null);

        Employee expectEmployee = createService.registerEmployee(employee);

        Employee addedEmployee = employeeRepository.findByAccountNumber(9);

        Assert.assertEquals(expectEmployee,addedEmployee);
    }

    @Test
    @Transactional
    public void registerCompanyTest() throws Exception{

    }

}