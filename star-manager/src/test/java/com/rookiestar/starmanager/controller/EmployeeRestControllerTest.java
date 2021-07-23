package com.rookiestar.starmanager.controller;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.constant.AttributeNames;
import com.rookiestar.starmanager.constant.Degrees;
import com.rookiestar.starmanager.constant.IdealPositions;
import com.rookiestar.starmanager.constant.UserTypes;
import com.rookiestar.starmanager.entity.assessment.Assessment;
import com.rookiestar.starmanager.entity.employee.Employee;
import com.rookiestar.starmanager.entity.employee.JobHunting;
import com.rookiestar.starmanager.entity.experience.Experience;
import com.rookiestar.starmanager.repository.AssessmentRepository;
import com.rookiestar.starmanager.repository.EmployeeRepository;
import com.rookiestar.starmanager.repository.ExperienceRepository;
import com.rookiestar.starmanager.repository.JobHuntingRepository;
import com.rookiestar.starmanager.service.RetrieveService;
import com.rookiestar.starmanager.shiro.token.GenericToken;
import com.rookiestar.starmanager.util.DataBaseUtil;
import com.rookiestar.starmanager.util.DataBaseUtilPages;
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

import java.util.Map;

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
    @Autowired
    private JobHuntingRepository jobHuntingRepository;

    private final Map<Integer, Employee> employeeMap;
    private final Map<Integer, Experience> experienceMap;
    private final Map<Integer, Assessment> assessmentMap;

    public EmployeeRestControllerTest() throws Exception{
        employeeMap = DataBaseUtil.getInstance().getEmployeeMap();
        experienceMap = DataBaseUtil.getInstance().getExperienceMap();
        assessmentMap = DataBaseUtil.getInstance().getAssessmentMap();
    }

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

        Employee employee = new Employee(employeeMap.get(5),new Experience(experienceMap.get(5121),assessmentMap.get(51)),new Experience(experienceMap.get(5221),assessmentMap.get(52)));
        employee.setName("张三1");
        employee.setGender("跨性别者");
        employee.setBirthday(DateUtil.parse("2001-01-20"));
        employee.setEmail("199");
        Employee actualEmployee1=employeeRepository.findByAccountNumber(5);
        Assert.assertNotEquals(employee,actualEmployee1);
        mvc.perform(MockMvcRequestBuilders.get("/updateEmployee.do")
                .param("accountNumber","5")
                .param("name","张三1")
                .param("gender","跨性别者")
                .param("birthday","2001-01-20")
                .param("email","199")
                .param("identifyNumber","5")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        Employee actualEmployee2=employeeRepository.findByAccountNumber(5);
        Assert.assertEquals(employee,actualEmployee2);
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

    @Test
    @Transactional
    public void goJobHuntingTest() throws Exception{
        DataBaseUtil.getInstance().initJobHunting(jobHuntingRepository);
        //map.put(6,new JobHunting(Degrees.DOCTOR,false, IdealPositions.BACKEND,6));
        JobHunting jobHunting=new JobHunting(Degrees.DOCTOR,true, IdealPositions.BACKEND,5);
        JobHunting actualJobHunting=jobHuntingRepository.findJobHuntingByAccountNumber(5);
        Assert.assertNotEquals(jobHunting,actualJobHunting);
        mvc.perform(MockMvcRequestBuilders.get("/goJobHunting.do?idealPosition=后端工程师&degree=博士")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("true")))
                .andDo(MockMvcResultHandlers.print());
        actualJobHunting=jobHuntingRepository.findJobHuntingByAccountNumber(5);
        Assert.assertEquals(jobHunting,actualJobHunting);
    }

    @Test
    @Transactional
    public void stopJobHuntingTest() throws Exception{
        DataBaseUtil.getInstance().initJobHunting(jobHuntingRepository);
        JobHunting jobHunting=new JobHunting("1",false,"1",5);
        JobHunting actualJobHunting=jobHuntingRepository.findJobHuntingByAccountNumber(5);
        Assert.assertNotEquals(jobHunting.isJobHunting(),actualJobHunting.isJobHunting());
        mvc.perform(MockMvcRequestBuilders.get("/stopJobHunting.do")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("true")))
                .andDo(MockMvcResultHandlers.print());
        actualJobHunting=jobHuntingRepository.findJobHuntingByAccountNumber(5);
        Assert.assertEquals(jobHunting.isJobHunting(),actualJobHunting.isJobHunting());
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