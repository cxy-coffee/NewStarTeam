package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.Company;
import com.rookiestar.starmanager.entity.Employee;
import com.rookiestar.starmanager.entity.Experience;
import com.rookiestar.starmanager.repository.EmployeeRepository;
import com.rookiestar.starmanager.repository.ExperienceRepository;
import com.rookiestar.starmanager.util.DataBaseUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chris
 * @date 2021/7/12
 * @time 15:21
 */
public class UpdateServiceImplTest extends BaseTest {
    @Autowired
    private UpdateService updateService;
    @Autowired
    private RetrieveService retrieveService;
    @Autowired
    private ExperienceRepository experienceRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    private final Map<Integer, Employee> employeeMap;
    private final Map<Integer, Experience> experienceMap;
    private final Map<Integer, Company> companyMap;

    public UpdateServiceImplTest() throws Exception{
        employeeMap = DataBaseUtil.getInstance().getEmployeeMap();
        experienceMap = DataBaseUtil.getInstance().getExperienceMap();
        companyMap = DataBaseUtil.getInstance().getCompanyMap();
    }

    @Test
    @Transactional
    public void updateEmployeeTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        Employee employee=new Employee("testName",new Date(),"男","947998108@qq.com","5",5,"1015",null);
        updateService.updateEmployee(employee);
        Employee actualEmployee =retrieveService.retrieveEmployeeByIdentifyNumber("5");
        Assert.assertEquals(employee,actualEmployee);

    }
}
