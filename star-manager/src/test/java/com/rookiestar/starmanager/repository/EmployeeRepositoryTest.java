package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.Employee;
import com.rookiestar.starmanager.util.DataBaseUtil;
import com.rookiestar.starmanager.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeRepositoryTest extends BaseTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ExperienceRepository experienceRepository;

    private final Map<Integer,Employee> employeeMap;

    public EmployeeRepositoryTest() throws Exception{
        employeeMap = DataBaseUtil.getInstance().getEmployeeMap();
    }

    @Test
    @Transactional
    public void findAllEmployeesByCompanyTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);

        List<Employee> employees = employeeRepository.findAllEmployeesByCompany(1);
        for (Employee employee:employees) {
            employee.setBirthday(DateUtil.format(employee.getBirthday()));
        }

        List<Employee> actualEmployees = new ArrayList<>(employeeMap.values());

        Assert.assertEquals(employees,actualEmployees);
    }

    @Test
    @Transactional
    public void findPresentEmployeesByCompanyTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);

        List<Employee> employees = employeeRepository.findPresentEmployeesByCompany(1);
        for (Employee employee:employees) {
            employee.setBirthday(DateUtil.format(employee.getBirthday()));
        }

        List<Employee> actualEmployees = new ArrayList<>();
        actualEmployees.add(employeeMap.get(5));
        actualEmployees.add(employeeMap.get(6));

        Assert.assertEquals(employees,actualEmployees);
    }

    @Test
    @Transactional
    public void findMaxAccountNumberTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);

        int max = employeeRepository.findMaxAccountNumber();

        Assert.assertEquals(max,8);
    }

    @Test
    @Transactional
    public void findByAccountNumberTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);

        Employee employee = employeeRepository.findByAccountNumber(6);

        Employee actualEmployee = employeeMap.get(6);

        Assert.assertEquals(employee,actualEmployee);

    }


    @Test
    @Transactional
    public void findEmployeesByNameContainingTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);


        List<Employee> employees=employeeRepository.findEmployeesByNameContaining("张");
        for (Employee employee:employees) {
            employee.setBirthday(DateUtil.format(employee.getBirthday()));
        }

        List<Employee> actualEmployees = new ArrayList<>();
        actualEmployees.add(employeeMap.get(5));

        Assert.assertEquals(employees,actualEmployees);
    }

    @Test
    @Transactional
    public void findEmployeeByIdentifyNumberTest()throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        Employee employee=employeeRepository.findByIdentifyNumber("5");

        employee.setBirthday(DateUtil.format(employee.getBirthday()));

        Employee actualEmployee=employeeMap.get(5);

        Assert.assertEquals(employee,actualEmployee);
    }

    @Test
    @Transactional
    public void findEmployeesByGenderTest()throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        List<Employee> employees=employeeRepository.findEmployeesByGender("男");
        for (Employee employee:employees) {
            employee.setBirthday(DateUtil.format(employee.getBirthday()));
        }
        List<Employee> actualEmployees=new ArrayList<>();
        actualEmployees.add(employeeMap.get(5));
        actualEmployees.add(employeeMap.get(7));

        Assert.assertEquals(employees,actualEmployees);
    }

}