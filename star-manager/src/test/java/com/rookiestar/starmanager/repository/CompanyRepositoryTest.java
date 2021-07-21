package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.company.Company;
import com.rookiestar.starmanager.entity.companymanager.CompanyManager;
import com.rookiestar.starmanager.util.DataBaseUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Test class that test CompanyRepository
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
public class CompanyRepositoryTest extends BaseTest {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private AssessmentRepository assessmentRepository;
    @Autowired
    private ExperienceRepository experienceRepository;

    public CompanyRepositoryTest() {
    }

    @Test
    @Transactional
    public void findMaxCompanyIdTest() throws Exception{
        DataBaseUtil.getInstance().initCompany(companyRepository);
        Assert.assertEquals(companyRepository.findMaxCompanyId(),2);
    }

    @Test
    @Transactional
    public void findByCompanyIdTest() throws Exception{
        DataBaseUtil.getInstance().initCompany(companyRepository);
        DataBaseUtil.getInstance().initAssessment(assessmentRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        DataBaseUtil.getInstance().initDepartment(departmentRepository);
        Company company=new Company(1,"我的公司1","Alan","lihaoc@whu.edu.cn","湖北省武汉市洪山区","88555273",null,null);
        Company actualCompany=companyRepository.findByCompanyId(1);
        Assert.assertEquals(actualCompany,company);
    }
}