package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.assessment.Assessment;
import com.rookiestar.starmanager.entity.company.Company;
import com.rookiestar.starmanager.entity.employee.Employee;
import com.rookiestar.starmanager.entity.experience.Experience;
import com.rookiestar.starmanager.repository.EmployeeRepository;
import com.rookiestar.starmanager.repository.ExperienceRepository;
import com.rookiestar.starmanager.util.DataBaseUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
    private final Map<Integer, Assessment> assessmentMap;

    public RetrieveServiceImplTest() throws Exception{
        employeeMap = DataBaseUtil.getInstance().getEmployeeMap();
        experienceMap = DataBaseUtil.getInstance().getExperienceMap();
        companyMap = DataBaseUtil.getInstance().getCompanyMap();
        assessmentMap = DataBaseUtil.getInstance().getAssessmentMap();
    }

    @Test
    @Transactional
    public void retrieveAllEmployeesByCompanyTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);

        List<Employee> employees = retrieveService.retrieveAllEmployeesByCompany(1);

        List<Employee> actualEmployees = new ArrayList<>();
        actualEmployees.add(new Employee(employeeMap.get(5),new Experience(experienceMap.get(5121),assessmentMap.get(51)),new Experience(experienceMap.get(5221),assessmentMap.get(52))));
        actualEmployees.add(new Employee(employeeMap.get(6),new Experience(experienceMap.get(6121),assessmentMap.get(61)),new Experience(experienceMap.get(6221),assessmentMap.get(62))));
        actualEmployees.add(new Employee(employeeMap.get(7),new Experience(experienceMap.get(7121),assessmentMap.get(71)),new Experience(experienceMap.get(7221),assessmentMap.get(72))));
        actualEmployees.add(new Employee(employeeMap.get(8),new Experience(experienceMap.get(8121),assessmentMap.get(81)),new Experience(experienceMap.get(8221),assessmentMap.get(82))));

        Assert.assertEquals(employees,actualEmployees);
    }

    @Test
    @Transactional
    public void retrievePresentEmployeesByCompanyTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);

        List<Employee> employees = retrieveService.retrievePresentEmployeesByCompany(1);

        List<Employee> actualEmployees = new ArrayList<>();
        actualEmployees.add(new Employee(employeeMap.get(5),new Experience(experienceMap.get(5121),assessmentMap.get(51)),new Experience(experienceMap.get(5221),assessmentMap.get(52))));
        actualEmployees.add(new Employee(employeeMap.get(6),new Experience(experienceMap.get(6121),assessmentMap.get(61)),new Experience(experienceMap.get(6221),assessmentMap.get(62))));

        Assert.assertEquals(employees,actualEmployees);
    }
    @Test
    @Transactional
    public void retrieveEmployeesByNameTest() throws Exception {
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);

        List<Employee> employees = retrieveService.retrieveEmployeesByName("张");

        List<Employee> actualEmployees = new ArrayList<>();
        actualEmployees.add(new Employee(employeeMap.get(5),new Experience(experienceMap.get(5121),assessmentMap.get(51)),new Experience(experienceMap.get(5221),assessmentMap.get(52))));

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
        actualEmployees.add(new Employee(employeeMap.get(5),new Experience(experienceMap.get(5121),assessmentMap.get(51)),new Experience(experienceMap.get(5221),assessmentMap.get(52))));
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
        actualEmployees.add(new Employee(employeeMap.get(5),new Experience(experienceMap.get(5121),assessmentMap.get(51)),new Experience(experienceMap.get(5221),assessmentMap.get(52))));
        actualEmployees.add(new Employee(employeeMap.get(7),new Experience(experienceMap.get(7121),assessmentMap.get(71)),new Experience(experienceMap.get(7221),assessmentMap.get(72))));
        Assert.assertEquals(employees,actualEmployees);
    }
}