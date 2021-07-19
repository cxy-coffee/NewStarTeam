package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.assessment.Assessment;
import com.rookiestar.starmanager.entity.company.Company;
import com.rookiestar.starmanager.entity.company.CompanyToReview;
import com.rookiestar.starmanager.entity.department.Department;
import com.rookiestar.starmanager.entity.employee.Employee;
import com.rookiestar.starmanager.entity.experience.Experience;
import com.rookiestar.starmanager.entity.position.Position;
import com.rookiestar.starmanager.repository.*;
import com.rookiestar.starmanager.util.DataBaseUtil;
import com.rookiestar.starmanager.util.DateUtil;
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
    private DepartmentRepository departmentRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private ExperienceRepository experienceRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyToReviewRepository companyToReviewRepository;
    @Autowired
    private AssessmentRepository assessmentRepository;
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
        DataBaseUtil.getInstance().initAssessment(assessmentRepository);
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
        DataBaseUtil.getInstance().initAssessment(assessmentRepository);

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
        DataBaseUtil.getInstance().initAssessment(assessmentRepository);

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
        DataBaseUtil.getInstance().initAssessment(assessmentRepository);
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
        DataBaseUtil.getInstance().initAssessment(assessmentRepository);
        List<Employee> employees=retrieveService.retrieveEmployeesByGender("男");
        List<Employee> actualEmployees = new ArrayList<>();
        actualEmployees.add(new Employee(employeeMap.get(5),new Experience(experienceMap.get(5121),assessmentMap.get(51)),new Experience(experienceMap.get(5221),assessmentMap.get(52))));
        actualEmployees.add(new Employee(employeeMap.get(7),new Experience(experienceMap.get(7121),assessmentMap.get(71)),new Experience(experienceMap.get(7221),assessmentMap.get(72))));
        Assert.assertEquals(employees,actualEmployees);
    }

    @Test
    @Transactional
    public void retrieveAssessmentByAccountNumberAndCompanyIdAndStartTimeTest()throws Exception{
        DataBaseUtil.getInstance().initAssessment(assessmentRepository);
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        Assessment assessment= retrieveService.retrieveAssessmentByAccountNumberAndCompanyIdAndStartTime(5,1, DateUtil.parse("2010-01-10"));
        Assessment actualAssessment=new Assessment(5,1,DateUtil.parse("2010-01-10"),"0/10","51的表现");
        Assert.assertEquals(assessment,actualAssessment);
    }

    @Test
    @Transactional
    public void retrieveExperienceByAccountNumberAndCompanyIdAndStartTimeTest()throws Exception{
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        Experience experience=retrieveService.retrieveExperienceByAccountNumberAndCompanyIdAndStartTime(5,1,DateUtil.parse("2010-01-10"));
        Experience actualExperience=new Experience(5,1,2,1,1521,DateUtil.parse("2010-01-10"),null,false);
        actualExperience.setAssessment(assessmentRepository.findByAccountNumberAndCompanyIdAndStartTime(actualExperience.getAccountNumber(), actualExperience.getCompanyId(), actualExperience.getStartTime()));
        Assert.assertEquals(experience,actualExperience);
    }

    @Test
    @Transactional
    public void retrieveEmployeeByEmailTest()throws  Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        Employee employee=retrieveService.retrieveEmployeeByEmail("2019302110260@whu.edu.cn");
        Employee actualEmployee=new Employee(employeeMap.get(5),experienceMap.get(5121),experienceMap.get(5221));
        actualEmployee.setExperiences(experienceRepository.findAllByAccountNumber(actualEmployee.getAccountNumber()));
        Assert.assertEquals(employee,actualEmployee);
    }

    @Test
    @Transactional
    public void retrieveDepartmentByCompanyIdAndDepartmentIdTest()throws Exception{
        DataBaseUtil.getInstance().initDepartment(departmentRepository);
        Department department=retrieveService.retrieveDepartmentByCompanyIdAndDepartmentId(1,1);
        Department actualDepartment=new Department(1,1,"公司1部门1",null);
        actualDepartment.setPositions(positionRepository.findByCompanyIdAndDepartmentId(actualDepartment.getCompanyId(), actualDepartment.getDepartmentId()));
        Assert.assertEquals(department,actualDepartment);
    }

    @Test
    @Transactional
    public void retrievePositionByCompanyIdAndDepartmentIdAndPositionIdTest()throws Exception{
        DataBaseUtil.getInstance().initPosition(positionRepository);
        Position position=retrieveService.retrievePositionByCompanyIdAndDepartmentIdAndPositionId(1,1,1);
        Position actualPosition=new Position(1,1,1,"公司1部门1员工");
        Assert.assertEquals(position,actualPosition);
    }

    @Test
    @Transactional
    public void retrieveEmployeeByAccountNumberTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        DataBaseUtil.getInstance().initAssessment(assessmentRepository);
        Employee employee = retrieveService.retrieveEmployeeByAccountNumber(5);
        Employee expectEmployee = new Employee(employeeMap.get(5),new Experience(experienceMap.get(5121),assessmentMap.get(51)),new Experience(experienceMap.get(5221),assessmentMap.get(52)));
        Assert.assertEquals(expectEmployee,employee);
    }
    @Test
    @Transactional
    public void retrieveAllCompanyToReviewTest()throws Exception{
        DataBaseUtil.getInstance().initCompanyToReview(companyToReviewRepository);
        List<CompanyToReview> companyToReviewList=new ArrayList<>();
        companyToReviewList.add(new CompanyToReview(1,"我的公司1","Alan","lihaoc@whu.edu.cn","湖北省武汉市洪山区","88555273"));
        companyToReviewList.add(new CompanyToReview(2,"我的公司2","Bob","2019302110243@whu.edu.cn","四川省成都市锦江区","88555573"));
        List<CompanyToReview> companyToReviewList1 = retrieveService.retrieveAllCompanyToReview();
        Assert.assertEquals(companyToReviewList,companyToReviewList1);
    }

}