package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.assessment.Assessment;
import com.rookiestar.starmanager.entity.company.Company;
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

import java.util.Date;
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
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private AssessmentRepository assessmentRepository;
    private final Map<Integer, Employee> employeeMap;
    private final Map<Integer, Experience> experienceMap;
    private final Map<Integer, Company> companyMap;
    private final Map<Integer,Assessment> assessmentMap;

    public UpdateServiceImplTest() throws Exception{
        employeeMap = DataBaseUtil.getInstance().getEmployeeMap();
        experienceMap = DataBaseUtil.getInstance().getExperienceMap();
        companyMap = DataBaseUtil.getInstance().getCompanyMap();
        assessmentMap = DataBaseUtil.getInstance().getAssessmentMap();
    }

    @Test
    @Transactional
    public void updateEmployeeTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        Employee employee=new Employee("testName",new Date(),"男","947998108@qq.com","5",5,"1015",null);
        Employee expectEmployee = new Employee(employee,new Experience(experienceMap.get(5121),assessmentMap.get(51)),new Experience(experienceMap.get(5221),assessmentMap.get(52)));
        updateService.updateEmployee(employee);
        Employee actualEmployee =retrieveService.retrieveEmployeeByIdentifyNumber("5");
        Assert.assertEquals(expectEmployee,actualEmployee);

    }

    @Test
    @Transactional
    public void updateAssessmentTest()throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        DataBaseUtil.getInstance().initAssessment(assessmentRepository);
        Assessment assessment = new Assessment(5, 1, DateUtil.parse("2010-01-10"), "10/10", "51的表现修改后");
        updateService.updateAssessment(assessment);
        Assessment actualAssessment=retrieveService.retrieveAssessmentByAccountNumberAndCompanyIdAndStartTime(5, 1, DateUtil.parse("2010-01-10"));
        Assert.assertEquals(assessment.getAbsenteeismRate()+assessment.getPerformance(),actualAssessment.getAbsenteeismRate()+actualAssessment.getPerformance());
    }

    @Test
    @Transactional
    public void updateExperienceTest() throws Exception{
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        Experience experience=new Experience(5,1,4396,7777,1521,DateUtil.parse("2010-01-10"),DateUtil.parse("2021-10-15"),true);
        experience.setAssessment(assessmentRepository.findByAccountNumberAndCompanyIdAndStartTime(experience.getAccountNumber(),experience.getCompanyId(),experience.getStartTime()));
        Experience actualExperience = retrieveService.retrieveExperienceByAccountNumberAndCompanyIdAndStartTime(5,1,DateUtil.parse("2010-01-10"));
        Assert.assertNotEquals(experience,actualExperience);
        updateService.updateExperience(experience);
        actualExperience = retrieveService.retrieveExperienceByAccountNumberAndCompanyIdAndStartTime(5,1,DateUtil.parse("2010-01-10"));
        Assert.assertEquals(experience,actualExperience);
    }

    @Test
    @Transactional
    public void updateDepartmentTest()throws Exception{
        DataBaseUtil.getInstance().initDepartment(departmentRepository);
        Department department=new Department(1,1,"公司1部门1234",positionRepository.findByCompanyIdAndDepartmentId(1,1));
        Department actualDepartment=retrieveService.retrieveDepartmentByCompanyIdAndDepartmentId(1,1);
        Assert.assertNotEquals(department,actualDepartment);
        updateService.updateDepartment(department);
        actualDepartment=retrieveService.retrieveDepartmentByCompanyIdAndDepartmentId(1,1);
        Assert.assertEquals(department,actualDepartment);
    }

    @Test
    @Transactional
    public void updatePositionTest()throws Exception{
        DataBaseUtil.getInstance().initPosition(positionRepository);
        Position position=new Position(1,1,1,"公司1部门1员工1234");
        Position actualPosition=retrieveService.retrievePositionByCompanyIdAndDepartmentIdAndPositionId(1,1,1);
        Assert.assertNotEquals(position,actualPosition);
        updateService.updatePosition(position);
        actualPosition=retrieveService.retrievePositionByCompanyIdAndDepartmentIdAndPositionId(1,1,1);
        Assert.assertEquals(position,actualPosition);
    }
}
