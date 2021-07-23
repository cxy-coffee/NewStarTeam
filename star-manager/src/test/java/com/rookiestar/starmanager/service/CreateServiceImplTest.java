package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.assessment.Assessment;
import com.rookiestar.starmanager.entity.company.Company;
import com.rookiestar.starmanager.entity.company.CompanyToReview;
import com.rookiestar.starmanager.entity.companymanager.CompanyManager;
import com.rookiestar.starmanager.entity.employee.Employee;
import com.rookiestar.starmanager.entity.experience.Experience;
import com.rookiestar.starmanager.repository.*;
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
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyToReviewRepository companyToReviewRepository;
    @Autowired
    private CompanyManagerRepository companyManagerRepository;

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
        DataBaseUtil.getInstance().initCompany(companyRepository);

        Company company=new Company("我的公司2","Bob","2019302110243@whu.edu.cn","四川省成都市锦江区","88555573");
        createService.registerCompany(company);
        Company actualCompany = companyRepository.findByCompanyId(3);

        Company expectCompany = new Company(3,"我的公司2","Bob","2019302110243@whu.edu.cn","四川省成都市锦江区","88555573",null,null);

        Assert.assertEquals(expectCompany,actualCompany);
    }

    @Test
    @Transactional
    public void addCompanyToReviewTest() throws Exception{
        DataBaseUtil.getInstance().initCompanyToReview(companyToReviewRepository);

        CompanyToReview companyToReview = new CompanyToReview("腾讯","马化腾","abcdef@qq.com","广东省深圳市南山区","1342525262");
        createService.addCompanyToReview(companyToReview);
        CompanyToReview actualCompanyToReview = companyToReviewRepository.findByCompanyId(3);

        CompanyToReview expectCompanyToReview = new CompanyToReview(3,"腾讯","马化腾","abcdef@qq.com","广东省深圳市南山区","1342525262");

        Assert.assertEquals(expectCompanyToReview,actualCompanyToReview);
    }

    @Test
    @Transactional
    public void initCompanyManagerTest() throws Exception{
        DataBaseUtil.getInstance().initCompanyManager(companyManagerRepository);

        Company company = new Company(114514,"测试公司","我是伞兵","2019302110260@whu.edu.cn","停机坪","8838438",null,null);
        createService.initCompanyManager(company);

        CompanyManager companyManager = companyManagerRepository.findByCompanyIdAndEmailAndJobNumber(114514, "2019302110260@whu.edu.cn", 123456);

        Assert.assertEquals(new CompanyManager(114514,"2019302110260@whu.edu.cn",123456,"123456"),companyManager);
    }

}