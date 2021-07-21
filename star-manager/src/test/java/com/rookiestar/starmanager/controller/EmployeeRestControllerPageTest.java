package com.rookiestar.starmanager.controller;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.repository.AssessmentRepository;
import com.rookiestar.starmanager.repository.EmployeeRepository;
import com.rookiestar.starmanager.repository.ExperienceRepository;
import com.rookiestar.starmanager.service.RetrieveService;
import com.rookiestar.starmanager.util.DataBaseUtilPages;
import org.hamcrest.Matchers;
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

/**
 * Description
 *
 * @author 曹向阳
 * @date 2021/7/21
 */
public class EmployeeRestControllerPageTest extends BaseTest {
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
                .param("accountNumber","25").param("password","123"));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult result = resultActions.andReturn();
        session = (MockHttpSession) result.getRequest().getSession();
        assert session != null;
    }

    @Test
    @Transactional
    public void selfRetrieveEmployeePageTest() throws Exception{
        DataBaseUtilPages.getInstance().initEmployee(employeeRepository);
        DataBaseUtilPages.getInstance().initExperience(experienceRepository);
        DataBaseUtilPages.getInstance().initAssessment(assessmentRepository);

        mvc.perform(MockMvcRequestBuilders.get("/selfRetrieveEmployeePage.do")
                .param("page","2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("{\"name\":\"跳槽哥\",\"birthday\":\"2000-01-01T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2875233439@qq.com\",\"identifyNumber\":\"25\",\"accountNumber\":25,\"password\":\"123\",\"experiences\":[{\"accountNumber\":25,\"companyId\":8,\"departmentId\":2,\"positionId\":1,\"jobNumber\":82521,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":{\"accountNumber\":25,\"companyId\":8,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"258的表现\"}},{\"accountNumber\":25,\"companyId\":9,\"departmentId\":2,\"positionId\":1,\"jobNumber\":92521,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":{\"accountNumber\":25,\"companyId\":9,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"259的表现\"}},{\"accountNumber\":25,\"companyId\":10,\"departmentId\":2,\"positionId\":1,\"jobNumber\":102521,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":{\"accountNumber\":25,\"companyId\":10,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"2510的表现\"}},{\"accountNumber\":25,\"companyId\":11,\"departmentId\":2,\"positionId\":1,\"jobNumber\":112521,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":{\"accountNumber\":25,\"companyId\":11,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"2511的表现\"}},{\"accountNumber\":25,\"companyId\":12,\"departmentId\":2,\"positionId\":1,\"jobNumber\":122521,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":{\"accountNumber\":25,\"companyId\":12,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"2512的表现\"}}]}")))
                .andDo(MockMvcResultHandlers.print());
    }
}
