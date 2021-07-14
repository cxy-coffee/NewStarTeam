package com.rookiestar.starmanager.controller;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.constant.AttributeNames;
import com.rookiestar.starmanager.entity.company.Company;
import com.rookiestar.starmanager.entity.department.Department;
import com.rookiestar.starmanager.entity.position.Position;
import com.rookiestar.starmanager.repository.*;
import com.rookiestar.starmanager.service.RetrieveService;
import com.rookiestar.starmanager.util.DataBaseUtil;
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

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class that test ManagerRestController
 *
 * @author 曹向阳
 * @date 2021/7/14
 */
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
        Company actualCompany=retrieveService.retrieveCompanyById(3);
        Company company=new Company(3,"我的公司2","Bob","2019302110243@whu.edu.cn","四川省成都市锦江区","88555573",null,null);
        Assert.assertEquals(company,actualCompany);
    }


    @Test
    @Transactional
    public void updateDepartmentTest() throws Exception{
        DataBaseUtil.getInstance().initDepartment(departmentRepository);
        Department department=new Department(1,1,"公司1部门12314",null);
        Department actualDepartment = retrieveService.retrieveDepartmentByCompanyIdAndDepartmentId(1, 1);
        Assert.assertNotEquals(department,actualDepartment);
        mvc.perform(MockMvcRequestBuilders.get("/updateDepartment.do?companyId=1&departmentId=1&name=公司1部门12314")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("true")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        actualDepartment=retrieveService.retrieveDepartmentByCompanyIdAndDepartmentId(1,1);
        Assert.assertEquals(department,actualDepartment);
    }

    @Test
    @Transactional
    public void updatePositionTest() throws Exception{
        DataBaseUtil.getInstance().initPosition(positionRepository);
        Position position=new Position(1,1,1,"hello test3");
        Position actualPosition=retrieveService.retrievePositionByCompanyIdAndDepartmentIdAndPositionId(1,1,1);
        Assert.assertNotEquals(position,actualPosition);
        mvc.perform(MockMvcRequestBuilders.get("/updatePosition.do?companyId=1&departmentId=1&positionId=1&name=hello test3")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("true")))
                .andDo(MockMvcResultHandlers.print());
        actualPosition=retrieveService.retrievePositionByCompanyIdAndDepartmentIdAndPositionId(1,1,1);
        Assert.assertEquals(position,actualPosition);
    }
}