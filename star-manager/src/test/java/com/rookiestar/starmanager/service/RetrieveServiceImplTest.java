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
import com.rookiestar.starmanager.util.DataBaseUtilPages;
import com.rookiestar.starmanager.util.DateUtil;
import com.rookiestar.starmanager.util.PageUtil;
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
    private CompanyRepository companyRepository;
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
        actualEmployees.add(new Employee(employeeMap.get(5),new Experience(experienceMap.get(5121),assessmentMap.get(51))));
        actualEmployees.add(new Employee(employeeMap.get(6),new Experience(experienceMap.get(6121),assessmentMap.get(61))));

        Assert.assertEquals(employees,actualEmployees);
    }

    @Test
    @Transactional
    public void retrievePresentEmployeesByCompanyPageTest() throws Exception{
        DataBaseUtilPages.getInstance().initEmployee(employeeRepository);
        DataBaseUtilPages.getInstance().initExperience(experienceRepository);
        DataBaseUtilPages.getInstance().initAssessment(assessmentRepository);

        Assert.assertEquals("[Employee{name='员工10', birthday=Fri Mar 12 00:00:00 CST 2010, gender='女', email='员工10的email', identifyNumber='10', accountNumber=10, password='123', experiences=[Experience{accountNumber=10, companyId=3, departmentId=1, positionId=1, jobNumber=31011, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=false, assessment=Assessment{accountNumber=10, companyId=3, startTime=Fri Mar 12 00:00:00 CST 2077, absenteeismRate='0/10', performance='103的表现'}}]}, Employee{name='员工11', birthday=Sat Mar 12 00:00:00 CST 2011, gender='男', email='员工11的email', identifyNumber='11', accountNumber=11, password='123', experiences=[Experience{accountNumber=11, companyId=3, departmentId=1, positionId=1, jobNumber=31111, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=false, assessment=Assessment{accountNumber=11, companyId=3, startTime=Fri Mar 12 00:00:00 CST 2077, absenteeismRate='0/10', performance='113的表现'}}]}, Employee{name='员工12', birthday=Mon Mar 12 00:00:00 CST 2012, gender='女', email='员工12的email', identifyNumber='12', accountNumber=12, password='123', experiences=[Experience{accountNumber=12, companyId=3, departmentId=1, positionId=1, jobNumber=31211, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=false, assessment=Assessment{accountNumber=12, companyId=3, startTime=Fri Mar 12 00:00:00 CST 2077, absenteeismRate='0/10', performance='123的表现'}}]}, Employee{name='员工13', birthday=Tue Mar 12 00:00:00 CST 2013, gender='男', email='员工13的email', identifyNumber='13', accountNumber=13, password='123', experiences=[Experience{accountNumber=13, companyId=3, departmentId=1, positionId=1, jobNumber=31311, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=false, assessment=Assessment{accountNumber=13, companyId=3, startTime=Fri Mar 12 00:00:00 CST 2077, absenteeismRate='0/10', performance='133的表现'}}]}, Employee{name='员工14', birthday=Wed Mar 12 00:00:00 CST 2014, gender='女', email='员工14的email', identifyNumber='14', accountNumber=14, password='123', experiences=[Experience{accountNumber=14, companyId=3, departmentId=1, positionId=1, jobNumber=31411, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=false, assessment=Assessment{accountNumber=14, companyId=3, startTime=Fri Mar 12 00:00:00 CST 2077, absenteeismRate='0/10', performance='143的表现'}}]}]",retrieveService.retrievePresentEmployeesByCompanyPage(3,1).toString());
        Assert.assertEquals("[Employee{name='员工20', birthday=Thu Mar 12 00:00:00 CST 2020, gender='女', email='员工20的email', identifyNumber='20', accountNumber=20, password='123', experiences=[Experience{accountNumber=20, companyId=3, departmentId=1, positionId=1, jobNumber=32011, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=false, assessment=Assessment{accountNumber=20, companyId=3, startTime=Fri Mar 12 00:00:00 CST 2077, absenteeismRate='0/10', performance='203的表现'}}]}, Employee{name='员工21', birthday=Fri Mar 12 00:00:00 CST 2021, gender='男', email='员工21的email', identifyNumber='21', accountNumber=21, password='123', experiences=[Experience{accountNumber=21, companyId=3, departmentId=1, positionId=1, jobNumber=32111, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=false, assessment=Assessment{accountNumber=21, companyId=3, startTime=Fri Mar 12 00:00:00 CST 2077, absenteeismRate='0/10', performance='213的表现'}}]}, Employee{name='员工22', birthday=Sat Mar 12 00:00:00 CST 2022, gender='女', email='员工22的email', identifyNumber='22', accountNumber=22, password='123', experiences=[Experience{accountNumber=22, companyId=3, departmentId=1, positionId=1, jobNumber=32211, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=false, assessment=Assessment{accountNumber=22, companyId=3, startTime=Fri Mar 12 00:00:00 CST 2077, absenteeismRate='0/10', performance='223的表现'}}]}, Employee{name='员工23', birthday=Sun Mar 12 00:00:00 CST 2023, gender='男', email='员工23的email', identifyNumber='23', accountNumber=23, password='123', experiences=[Experience{accountNumber=23, companyId=3, departmentId=1, positionId=1, jobNumber=32311, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=false, assessment=Assessment{accountNumber=23, companyId=3, startTime=Fri Mar 12 00:00:00 CST 2077, absenteeismRate='0/10', performance='233的表现'}}]}, Employee{name='跳槽哥', birthday=Sat Jan 01 00:00:00 CST 2000, gender='男', email='2875233439@qq.com', identifyNumber='25', accountNumber=25, password='123', experiences=[Experience{accountNumber=25, companyId=3, departmentId=2, positionId=1, jobNumber=32521, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=false, assessment=Assessment{accountNumber=25, companyId=3, startTime=Fri Mar 12 00:00:00 CST 2077, absenteeismRate='0/10', performance='253的表现'}}]}]",retrieveService.retrievePresentEmployeesByCompanyPage(3,3).toString());
        Assert.assertEquals("[]",retrieveService.retrievePresentEmployeesByCompanyPage(3,4).toString());
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
        Department actualDepartment=new Department(1,1,"部门1",null);
        actualDepartment.setPositions(positionRepository.findByCompanyIdAndDepartmentId(actualDepartment.getCompanyId(), actualDepartment.getDepartmentId()));
        Assert.assertEquals(department,actualDepartment);
    }

    @Test
    @Transactional
    public void retrievePositionByCompanyIdAndDepartmentIdAndPositionIdTest()throws Exception{
        DataBaseUtil.getInstance().initPosition(positionRepository);
        Position position=retrieveService.retrievePositionByCompanyIdAndDepartmentIdAndPositionId(1,1,1);
        Position actualPosition=new Position(1,1,1,"员工");
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

//    List<Employee> retrieveEmployeesByCompanyIdAndName(int companyId,String name);
//
//    List<Employee> retrieveEmployeesByCompanyIdAndGender(int companyId, String gender);
//
//    Employee retrieveEmployeesByCompanyIdAndEmail(int companyId, String email);
//
//    Employee retrieveEmployeesByCompanyIdAndIdentifyNumber(int companyId, String identifyNumber);


    @Test
    @Transactional
    public void retrieveEmployeesByCompanyIdAndNameTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        DataBaseUtil.getInstance().initAssessment(assessmentRepository);
        Employee employee=new Employee("张三",DateUtil.parse("2000-01-10"),"男","2019302110260@whu.edu.cn","5",5,"123",null);
        employee.setExperiences(experienceRepository.findByCompanyIdAndAccountNumber(1,5));
        List<Employee> employees=new ArrayList<>();
        employees.add(employee);
        List<Employee> actualEmployees=retrieveService.retrieveEmployeesByCompanyIdAndName(1,"三");
        Assert.assertEquals(employees,actualEmployees);
    }
    @Test
    @Transactional
    public void retrieveEmployeesByCompanyIdAndNamePageTest() throws Exception{
        DataBaseUtilPages.getInstance().initEmployee(employeeRepository);
        DataBaseUtilPages.getInstance().initExperience(experienceRepository);
        DataBaseUtilPages.getInstance().initAssessment(assessmentRepository);

        Assert.assertEquals("[Employee{name='员工20', birthday=Thu Mar 12 00:00:00 CST 2020, gender='女', email='员工20的email', identifyNumber='20', accountNumber=20, password='123', experiences=[Experience{accountNumber=20, companyId=3, departmentId=1, positionId=1, jobNumber=32011, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=false, assessment=Assessment{accountNumber=20, companyId=3, startTime=Fri Mar 12 00:00:00 CST 2077, absenteeismRate='0/10', performance='203的表现'}}]}, Employee{name='员工21', birthday=Fri Mar 12 00:00:00 CST 2021, gender='男', email='员工21的email', identifyNumber='21', accountNumber=21, password='123', experiences=[Experience{accountNumber=21, companyId=3, departmentId=1, positionId=1, jobNumber=32111, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=false, assessment=Assessment{accountNumber=21, companyId=3, startTime=Fri Mar 12 00:00:00 CST 2077, absenteeismRate='0/10', performance='213的表现'}}]}, Employee{name='员工22', birthday=Sat Mar 12 00:00:00 CST 2022, gender='女', email='员工22的email', identifyNumber='22', accountNumber=22, password='123', experiences=[Experience{accountNumber=22, companyId=3, departmentId=1, positionId=1, jobNumber=32211, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=false, assessment=Assessment{accountNumber=22, companyId=3, startTime=Fri Mar 12 00:00:00 CST 2077, absenteeismRate='0/10', performance='223的表现'}}]}, Employee{name='员工23', birthday=Sun Mar 12 00:00:00 CST 2023, gender='男', email='员工23的email', identifyNumber='23', accountNumber=23, password='123', experiences=[Experience{accountNumber=23, companyId=3, departmentId=1, positionId=1, jobNumber=32311, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=false, assessment=Assessment{accountNumber=23, companyId=3, startTime=Fri Mar 12 00:00:00 CST 2077, absenteeismRate='0/10', performance='233的表现'}}]}]",retrieveService.retrieveEmployeesByCompanyIdAndNamePage(3,"工", 3).toString());
        Assert.assertEquals("[]",retrieveService.retrieveEmployeesByCompanyIdAndNamePage(3,"工", 4).toString());
    }
    @Test
    @Transactional
    public void retrieveEmployeesByCompanyIdAndIdentifyNumberTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        Employee employee=new Employee("张三",DateUtil.parse("2000-01-10"),"男","2019302110260@whu.edu.cn","5",5,"123",null);
        employee.setExperiences(experienceRepository.findByCompanyIdAndAccountNumber(1,5));

        Employee actualEmployee=retrieveService.retrieveEmployeesByCompanyIdAndIdentifyNumber(1,"5");
        Assert.assertEquals(employee,actualEmployee);
    }
    @Test
    @Transactional
    public void retrieveEmployeesByCompanyIdAndEmailTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        Employee employee=new Employee("张三",DateUtil.parse("2000-01-10"),"男","2019302110260@whu.edu.cn","5",5,"123",null);
        employee.setExperiences(experienceRepository.findByCompanyIdAndAccountNumber(1,5));
        Employee actualEmployee=retrieveService.retrieveEmployeesByCompanyIdAndEmail(1,"2019302110260@whu.edu.cn");
        Assert.assertEquals(employee,actualEmployee);
    }
    @Test
    @Transactional
    public void retrieveEmployeesByCompanyIdAndGenderTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        DataBaseUtil.getInstance().initAssessment(assessmentRepository);
        Employee employee=new Employee("张三",DateUtil.parse("2000-01-10"),"男","2019302110260@whu.edu.cn","5",5,"123",null);
        employee.setExperiences(experienceRepository.findByCompanyIdAndAccountNumber(1,5));
        List<Employee> employees=new ArrayList<>();
        employees.add(employee);
        List<Employee> actualEmployees=retrieveService.retrieveEmployeesByCompanyIdAndGender(1,"男");
        Assert.assertEquals(employees,actualEmployees);
    }
    @Test
    @Transactional
    public void retrieveEmployeesByCompanyIdAndGenderPageTest() throws Exception{
        DataBaseUtilPages.getInstance().initEmployee(employeeRepository);
        DataBaseUtilPages.getInstance().initExperience(experienceRepository);
        DataBaseUtilPages.getInstance().initAssessment(assessmentRepository);

        Assert.assertEquals("[Employee{name='员工21', birthday=Fri Mar 12 00:00:00 CST 2021, gender='男', email='员工21的email', identifyNumber='21', accountNumber=21, password='123', experiences=[Experience{accountNumber=21, companyId=3, departmentId=1, positionId=1, jobNumber=32111, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=false, assessment=Assessment{accountNumber=21, companyId=3, startTime=Fri Mar 12 00:00:00 CST 2077, absenteeismRate='0/10', performance='213的表现'}}]}, Employee{name='员工23', birthday=Sun Mar 12 00:00:00 CST 2023, gender='男', email='员工23的email', identifyNumber='23', accountNumber=23, password='123', experiences=[Experience{accountNumber=23, companyId=3, departmentId=1, positionId=1, jobNumber=32311, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=false, assessment=Assessment{accountNumber=23, companyId=3, startTime=Fri Mar 12 00:00:00 CST 2077, absenteeismRate='0/10', performance='233的表现'}}]}, Employee{name='跳槽哥', birthday=Sat Jan 01 00:00:00 CST 2000, gender='男', email='2875233439@qq.com', identifyNumber='25', accountNumber=25, password='123', experiences=[Experience{accountNumber=25, companyId=3, departmentId=2, positionId=1, jobNumber=32521, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=false, assessment=Assessment{accountNumber=25, companyId=3, startTime=Fri Mar 12 00:00:00 CST 2077, absenteeismRate='0/10', performance='253的表现'}}]}]",retrieveService.retrieveEmployeesByCompanyIdAndGender(3,"男",2).toString());
        Assert.assertEquals("[]",retrieveService.retrieveEmployeesByCompanyIdAndGender(3,"男",3).toString());
    }

    @Test
    @Transactional
    public void retrieveCompanyByCompanyIdTest()throws Exception{
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        DataBaseUtil.getInstance().initCompany(companyRepository);
        DataBaseUtil.getInstance().initDepartment(departmentRepository);
        DataBaseUtil.getInstance().initPosition(positionRepository);
        DataBaseUtil.getInstance().initAssessment(assessmentRepository);
        Company company=new Company(1,"我的公司1","Alan","lihaoc@whu.edu.cn","湖北省武汉市洪山区","88555273",null,null);
        company.setDepartments(departmentRepository.findByCompanyId(company.getCompanyId()));
        company.setExperiences(experienceRepository.findByCompanyId(1));
        Company actualCompany=retrieveService.retrieveCompanyByCompanyId(1);
        System.out.println(actualCompany.toString());
        Assert.assertEquals(company,actualCompany);

    }

    @Test
    @Transactional
    public void retrieveEmployeeByAccountNumberPage() throws Exception{
        DataBaseUtilPages.getInstance().initEmployee(employeeRepository);
        DataBaseUtilPages.getInstance().initExperience(experienceRepository);
        DataBaseUtilPages.getInstance().initAssessment(assessmentRepository);

        Employee employee2 = retrieveService.retrieveEmployeeByAccountNumberPage(25,2);
        Assert.assertEquals("Employee{name='跳槽哥', birthday=Sat Jan 01 00:00:00 CST 2000, gender='男', email='2875233439@qq.com', identifyNumber='25', accountNumber=25, password='123', experiences=[Experience{accountNumber=25, companyId=8, departmentId=2, positionId=1, jobNumber=82521, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=true, assessment=Assessment{accountNumber=25, companyId=8, startTime=Fri Mar 12 00:00:00 CST 2077, absenteeismRate='0/10', performance='258的表现'}}, Experience{accountNumber=25, companyId=9, departmentId=2, positionId=1, jobNumber=92521, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=true, assessment=Assessment{accountNumber=25, companyId=9, startTime=Fri Mar 12 00:00:00 CST 2077, absenteeismRate='0/10', performance='259的表现'}}, Experience{accountNumber=25, companyId=10, departmentId=2, positionId=1, jobNumber=102521, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=true, assessment=Assessment{accountNumber=25, companyId=10, startTime=Fri Mar 12 00:00:00 CST 2077, absenteeismRate='0/10', performance='2510的表现'}}, Experience{accountNumber=25, companyId=11, departmentId=2, positionId=1, jobNumber=112521, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=true, assessment=Assessment{accountNumber=25, companyId=11, startTime=Fri Mar 12 00:00:00 CST 2077, absenteeismRate='0/10', performance='2511的表现'}}, Experience{accountNumber=25, companyId=12, departmentId=2, positionId=1, jobNumber=122521, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=true, assessment=Assessment{accountNumber=25, companyId=12, startTime=Fri Mar 12 00:00:00 CST 2077, absenteeismRate='0/10', performance='2512的表现'}}]}",employee2.toString());
    }

    @Test
    @Transactional
    public void retrieveAllCompanyToReviewPageTest() throws Exception{
        DataBaseUtilPages.getInstance().initCompanyToReview(companyToReviewRepository);

        Assert.assertEquals("[CompanyToReview{companyId=1, name='我的公司1', legalRepresentativeName='Alan', email='lihaoc@whu.edu.cn', address='湖北省武汉市洪山区', phone='88555273'}, CompanyToReview{companyId=2, name='我的公司2', legalRepresentativeName='Bob', email='2019302110243@whu.edu.cn', address='四川省成都市锦江区', phone='88555573'}, CompanyToReview{companyId=3, name='我的公司3', legalRepresentativeName='Bob', email='2019302110243@whu.edu.cn', address='四川省成都市锦江区', phone='88555573'}, CompanyToReview{companyId=4, name='我的公司4', legalRepresentativeName='Bob', email='2019302110243@whu.edu.cn', address='四川省成都市锦江区', phone='88555573'}, CompanyToReview{companyId=5, name='我的公司5', legalRepresentativeName='Bob', email='2019302110243@whu.edu.cn', address='四川省成都市锦江区', phone='88555573'}]",retrieveService.retrieveAllCompanyToReviewPage(1).toString());

        Assert.assertEquals("[CompanyToReview{companyId=6, name='我的公司6', legalRepresentativeName='Bob', email='2019302110243@whu.edu.cn', address='四川省成都市锦江区', phone='88555573'}, CompanyToReview{companyId=7, name='我的公司7', legalRepresentativeName='Bob', email='2019302110243@whu.edu.cn', address='四川省成都市锦江区', phone='88555573'}, CompanyToReview{companyId=8, name='我的公司8', legalRepresentativeName='Bob', email='2019302110243@whu.edu.cn', address='四川省成都市锦江区', phone='88555573'}]",retrieveService.retrieveAllCompanyToReviewPage(2).toString());
        Assert.assertEquals("[]",retrieveService.retrieveAllCompanyToReviewPage(3).toString());
    }

}