package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.Company;
import com.rookiestar.starmanager.entity.Employee;
import com.rookiestar.starmanager.entity.Experience;
import com.rookiestar.starmanager.repository.CompanyRepository;
import com.rookiestar.starmanager.repository.EmployeeRepository;
import com.rookiestar.starmanager.repository.ExperienceRepository;
import com.rookiestar.starmanager.util.DataBaseUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Test class that test RetrieveServiceImpl
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
public class RetrieveServiceImplTest extends BaseTest {
    @Autowired
    private RetrieveService retrieveService;

    @Autowired
    private ExperienceRepository experienceRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    private final Map<Integer,Employee> employeeMap;
    private final Map<Integer,Experience> experienceMap;
    private final Map<Integer, Company> companyMap;

    public RetrieveServiceImplTest() throws Exception{
        employeeMap = DataBaseUtil.getInstance().getEmployeeMap();
        experienceMap = DataBaseUtil.getInstance().getExperienceMap();
        companyMap = DataBaseUtil.getInstance().getCompanyMap();
    }

    @Test
    @Transactional
    public void retrieveAllEmployeesByCompanyTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);

        List<Employee> employees = retrieveService.retrieveAllEmployeesByCompany(1);

        List<Employee> actualEmployees = new ArrayList<>();

        actualEmployees.add(new Employee(employeeMap.get(5),experienceMap.get(5121),experienceMap.get(5221)));
        actualEmployees.add(new Employee(employeeMap.get(6),experienceMap.get(6121),experienceMap.get(6221)));
        actualEmployees.add(new Employee(employeeMap.get(7),experienceMap.get(7121),experienceMap.get(7221)));
        actualEmployees.add(new Employee(employeeMap.get(8),experienceMap.get(8121),experienceMap.get(8221)));

        Assert.assertEquals(employees,actualEmployees);
    }

    @Test
    @Transactional
    public void retrievePresentEmployeesByCompanyTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);

        List<Employee> employees = retrieveService.retrievePresentEmployeesByCompany(1);

        List<Employee> actualEmployees = new ArrayList<>();
        actualEmployees.add(new Employee(employeeMap.get(5),experienceMap.get(5121),experienceMap.get(5221)));
        actualEmployees.add(new Employee(employeeMap.get(6),experienceMap.get(6121),experienceMap.get(6221)));

        Assert.assertEquals(employees,actualEmployees);
    }
    @Test
    @Transactional
    public void retrieveEmployeesByNameTest() throws Exception {
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);

        List<Employee> employees = retrieveService.retrieveEmployeesByName("张");

        List<Employee> actualEmployees = new ArrayList<>();
        actualEmployees.add(new Employee(employeeMap.get(5),experienceMap.get(5121),experienceMap.get(5221)));

        Assert.assertEquals(employees,actualEmployees);

    }

    @Test
    @Transactional
    public void retrieveEmployeeByIdentifyNumberTest()throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        List<Employee> employees=new ArrayList<>();
        employees.add(retrieveService.retrieveEmployeeByIdentifyNumber("5"));
        List<Employee> actualEmployees = new ArrayList<>();
        actualEmployees.add(new Employee(employeeMap.get(5),experienceMap.get(5121),experienceMap.get(5221)));
        Assert.assertEquals(employees,actualEmployees);
    }

    @Test
    @Transactional
    public void retrieveEmployeesByGenderTest()throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        List<Employee> employees=new ArrayList<>();
        employees=retrieveService.retrieveEmployeesByGender("男");
        List<Employee> actualEmployees = new ArrayList<>();
        actualEmployees.add(new Employee(employeeMap.get(5),experienceMap.get(5121),experienceMap.get(5221)));
        actualEmployees.add(new Employee(employeeMap.get(7),experienceMap.get(7121),experienceMap.get(7221)));
        Assert.assertEquals(employees,actualEmployees);
    }
}