package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.Employee;
import com.rookiestar.starmanager.util.DataBaseUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EmployeeRepositoryTest extends BaseTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ExperienceRepository experienceRepository;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
            employee.setBirthday(sdf.parse(sdf.format(employee.getBirthday())));
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
            employee.setBirthday(sdf.parse(sdf.format(employee.getBirthday())));
        }

        List<Employee> actualEmployees = new ArrayList<>();
        actualEmployees.add(employeeMap.get(5));
        actualEmployees.add(employeeMap.get(6));

        Assert.assertEquals(employees,actualEmployees);
    }

}