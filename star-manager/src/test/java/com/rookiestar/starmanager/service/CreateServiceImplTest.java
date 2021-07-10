package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.Employee;
import com.rookiestar.starmanager.entity.Experience;
import com.rookiestar.starmanager.repository.EmployeeRepository;
import com.rookiestar.starmanager.repository.ExperienceRepository;
import com.rookiestar.starmanager.util.DataBaseUtil;
import com.rookiestar.starmanager.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
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

    @Test
    @Transactional
    public void hireEmployeeTest() throws Exception{
        Experience experience = new Experience();
        experience.setAccountNumber(9);
        experience.setCompanyId(1);
        experience.setDepartmentId(1);
        experience.setPositionId(1);

        Experience expectExperience = createService.hireEmployee(experience);
        //System.out.println("expectExperience:"+expectExperience);
        /*
        expectExperience:Experience{accountNumber=9, companyId=1, departmentId=1, positionId=1, jobNumber=9111, startTime=Fri Jul 09 00:00:00 CST 2021, endTime=null, isEnd=false, assessment=null}
         */

        Experience addedExperience = experienceRepository.findByAccountNumberAndCompanyIdAndDepartmentIdAndPositionId(9,1,1,1);
        //System.out.println("addedExperience:"+addedExperience);
        /*
        addedExperience:Experience{accountNumber=9, companyId=1, departmentId=1, positionId=1, jobNumber=9111, startTime=Fri Jul 09 00:00:00 CST 2021, endTime=null, isEnd=false, assessment=null}
         */
        //addedExperience.setStartTime(DateUtil.format(addedExperience.getStartTime()));

        Assert.assertEquals(expectExperience,addedExperience);
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

}