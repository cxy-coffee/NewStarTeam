package com.rookiestar.starmanager.controller;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.company.Company;
import com.rookiestar.starmanager.entity.company.CompanyToReview;
import com.rookiestar.starmanager.entity.department.Department;
import com.rookiestar.starmanager.entity.position.Position;
import com.rookiestar.starmanager.repository.*;
import com.rookiestar.starmanager.service.RetrieveService;
import com.rookiestar.starmanager.util.DataBaseUtil;
import com.rookiestar.starmanager.util.DataBaseUtilPages;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class that test ManagerRestController
 *
 * @author 曹向阳
 * @date 2021/7/14
 */
@SuppressWarnings("ALL")
public class ManagerRestControllerTest extends BaseTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;
    private MockHttpSession session;

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private RetrieveService retrieveService;
    @Autowired
    private ExperienceRepository experienceRepository;
    @Autowired
    private CompanyToReviewRepository companyToReviewRepository;
    @Autowired
    private CompanyManagerRepository companyManagerRepository;

    @Before
    public void setUp() throws Exception{
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(wac);
        builder.addFilters((Filter)wac.getBean("shiroFilter"));
        mvc=builder.build();

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/managerLogin.do")
                .param("accountNumber","666").param("password","888"));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult result = resultActions.andReturn();
        session = (MockHttpSession) result.getRequest().getSession();
        assert session != null;
    }

    @Test
    @Transactional
    public void confirmCompanyRegisterApplyTest()throws  Exception{
        DataBaseUtil.getInstance().initCompany(companyRepository);
        mvc.perform(MockMvcRequestBuilders.get("/confirmCompanyRegisterApply.do?companyId=2&name=我的公司2&legalRepresentativeName=Bob&email=2019302110243@whu.edu.cn&address=四川省成都市锦江区&phone=88555573")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("{\"companyId\":3,\"name\":\"我的公司2\",\"legalRepresentativeName\":\"Bob\",\"email\":\"2019302110243@whu.edu.cn\",\"address\":\"四川省成都市锦江区\",\"phone\":\"88555573\",\"experiences\":null,\"departments\":null}")))
                .andDo(MockMvcResultHandlers.print());
        Company actualCompany=companyRepository.findByCompanyId(3);
        actualCompany.setDepartments(departmentRepository.findByCompanyId(actualCompany.getCompanyId()));
        actualCompany.setExperiences(experienceRepository.findByCompanyId(actualCompany.getCompanyId()));
        Company company=new Company(3,"我的公司2","Bob","2019302110243@whu.edu.cn","四川省成都市锦江区","88555573",null,null);
        company.setExperiences(experienceRepository.findByCompanyId(company.getCompanyId()));
        company.setDepartments(departmentRepository.findByCompanyId(company.getCompanyId()));
        Assert.assertEquals(company,actualCompany);
    }

    @Test
    @Transactional
    public void getCompanyToReviewTest() throws Exception{
        DataBaseUtil.getInstance().initCompanyToReview(companyToReviewRepository);
        mvc.perform(MockMvcRequestBuilders.get("/getCompanyToReview.do")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"companyId\":1,\"name\":\"我的公司1\",\"legalRepresentativeName\":\"Alan\",\"email\":\"lihaoc@whu.edu.cn\",\"address\":\"湖北省武汉市洪山区\",\"phone\":\"88555273\"},{\"companyId\":2,\"name\":\"我的公司2\",\"legalRepresentativeName\":\"Bob\",\"email\":\"2019302110243@whu.edu.cn\",\"address\":\"四川省成都市锦江区\",\"phone\":\"88555573\"}]")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    public void getCompanyToReviewPageTest() throws Exception{
        DataBaseUtilPages.getInstance().initCompanyToReview(companyToReviewRepository);
        mvc.perform(MockMvcRequestBuilders.get("/getCompanyToReviewPage.do")
                .param("page","2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"companyId\":6,\"name\":\"我的公司6\",\"legalRepresentativeName\":\"Bob\",\"email\":\"2019302110243@whu.edu.cn\",\"address\":\"四川省成都市锦江区\",\"phone\":\"88555573\"},{\"companyId\":7,\"name\":\"我的公司7\",\"legalRepresentativeName\":\"Bob\",\"email\":\"2019302110243@whu.edu.cn\",\"address\":\"四川省成都市锦江区\",\"phone\":\"88555573\"},{\"companyId\":8,\"name\":\"我的公司8\",\"legalRepresentativeName\":\"Bob\",\"email\":\"2019302110243@whu.edu.cn\",\"address\":\"四川省成都市锦江区\",\"phone\":\"88555573\"}]")))
                .andDo(MockMvcResultHandlers.print());

        mvc.perform(MockMvcRequestBuilders.get("/getCompanyToReviewPage.do")
                .param("page","3")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[]")))
                .andDo(MockMvcResultHandlers.print());
    }
}