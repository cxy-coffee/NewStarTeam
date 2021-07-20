package com.rookiestar.starmanager.controller;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.constant.AttributeNames;
import com.rookiestar.starmanager.constant.UserTypes;
import com.rookiestar.starmanager.entity.employee.Employee;
import com.rookiestar.starmanager.repository.AssessmentRepository;
import com.rookiestar.starmanager.repository.EmployeeRepository;
import com.rookiestar.starmanager.repository.ExperienceRepository;
import com.rookiestar.starmanager.service.RetrieveService;
import com.rookiestar.starmanager.shiro.token.GenericToken;
import com.rookiestar.starmanager.util.DataBaseUtil;
import com.rookiestar.starmanager.util.DateUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
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
 * Description
 *
 * @author 曹向阳
 * @date 2021/7/14
 */
@SuppressWarnings("ALL")
public class EmployeeRestControllerTest extends BaseTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;
    @Autowired
    private MockHttpSession session;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ExperienceRepository experienceRepository;
    @Autowired
    private AssessmentRepository assessmentRepository;
    @Autowired
    private RetrieveService retrieveService;


    @Before
    public void setUp() throws Exception{
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(wac);
        builder.addFilters((Filter)wac.getBean("shiroFilter"));
        mvc=builder.build();

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/employeeLogin.do")
                .param("accountNumber","5").param("password","123"));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult result = resultActions.andReturn();
        session = (MockHttpSession) result.getRequest().getSession();
        assert session != null;
    }

    @Test
    @Transactional
    public void updateEmployeeTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);

        Employee employee=new Employee("张三", DateUtil.parse("2001-01-20"),"男","199","5",5,"123",null);
        Employee actualEmployee=DataBaseUtil.getInstance().getEmployeeMap().get(5);
        System.out.println(actualEmployee.getPassword());
        employee.setExperiences(experienceRepository.findAllByAccountNumber(5));
        Assert.assertNotEquals(employee,actualEmployee);
        mvc.perform(MockMvcRequestBuilders.get("/updateEmployee.do?birthday=2001-01-20&email=199&identifyNumber=5")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        actualEmployee=retrieveService.retrieveEmployeeByIdentifyNumber("5");
        Assert.assertEquals(actualEmployee,employee);
    }

    @Test
    @Transactional
    public void selfRetrieveEmployeeTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        DataBaseUtil.getInstance().initAssessment(assessmentRepository);

        mvc.perform(MockMvcRequestBuilders.get("/selfRetrieveEmployee.do")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("{\"name\":\"张三\",\"birthday\":\"2000-01-10T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2019302110260@whu.edu.cn\",\"identifyNumber\":\"5\",\"accountNumber\":5,\"password\":\"123\",\"experiences\":[{\"accountNumber\":5,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":5,\"companyId\":1,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"51的表现\"}},{\"accountNumber\":5,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":{\"accountNumber\":5,\"companyId\":2,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"52的表现\"}}]}")))
                .andDo(MockMvcResultHandlers.print());
    }


    /*
    @Test
    @Transactional
    public void func() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/url")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("").isNumber())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("")))
                .andDo(MockMvcResultHandlers.print());
    }
     */
}