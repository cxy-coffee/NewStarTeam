package com.rookiestar.starmanager.controller;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.constant.AttributeNames;
import com.rookiestar.starmanager.repository.*;
import com.rookiestar.starmanager.service.RetrieveService;
import com.rookiestar.starmanager.util.DataBaseUtil;
import com.rookiestar.starmanager.util.DataBaseUtilPages;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
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
public class CompanyRestControllerPageTest extends BaseTest {
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
    private EmployeeRepository employeeRepository;
    @Autowired
    private ExperienceRepository experienceRepository;
    @Autowired
    private AssessmentRepository assessmentRepository;
    @Autowired
    private RetrieveService retrieveService;
    @Autowired
    private CompanyToReviewRepository companyToReviewRepository;

    @Before
    public void setUp() throws Exception{
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(wac);
        builder.addFilters((Filter)wac.getBean("shiroFilter"));
        mvc=builder.build();

        ResultActions resultActions1 = mvc.perform(MockMvcRequestBuilders.get("/sendEmailCode.do")
                .param("to","2019302110260@whu.edu.cn"));
        resultActions1.andExpect(MockMvcResultMatchers.status().isOk());
        session = (MockHttpSession) resultActions1.andReturn().getRequest().getSession();
        assert session != null;

        ResultActions resultActions2 = mvc.perform(MockMvcRequestBuilders.get("/checkVerificationCode.do")
                .param("verificationCode",session.getAttribute(AttributeNames.VERIFICATION_CODE).toString()).session(session));
        resultActions2.andExpect(MockMvcResultMatchers.status().isOk());
        session = (MockHttpSession) resultActions2.andReturn().getRequest().getSession();
        assert session != null;

        ResultActions resultActions3 = mvc.perform(MockMvcRequestBuilders.get("/companyLogin.do")
                .param("companyId","3").param("jobNumber","32521").param("password","234").session(session));
        resultActions3.andExpect(MockMvcResultMatchers.status().isOk());
        session = (MockHttpSession) resultActions3.andReturn().getRequest().getSession();
        assert session != null;
    }

    @Test
    @Transactional
    public void getPresentEmployeesPageTest() throws Exception{
        DataBaseUtilPages.getInstance().initExperience(experienceRepository);
        DataBaseUtilPages.getInstance().initEmployee(employeeRepository);
        DataBaseUtilPages.getInstance().initAssessment(assessmentRepository);

        mvc.perform(MockMvcRequestBuilders.get("/getPresentEmployeesPage.do")
                .param("page","3")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"name\":\"员工20\",\"birthday\":\"2020-03-12T00:00:00.000+08:00\",\"gender\":\"女\",\"email\":\"员工20的email\",\"identifyNumber\":\"20\",\"accountNumber\":20,\"password\":\"123\",\"experiences\":[{\"accountNumber\":20,\"companyId\":3,\"departmentId\":1,\"positionId\":1,\"jobNumber\":32011,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":20,\"companyId\":3,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"203的表现\"}}]},{\"name\":\"员工21\",\"birthday\":\"2021-03-12T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"员工21的email\",\"identifyNumber\":\"21\",\"accountNumber\":21,\"password\":\"123\",\"experiences\":[{\"accountNumber\":21,\"companyId\":3,\"departmentId\":1,\"positionId\":1,\"jobNumber\":32111,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":21,\"companyId\":3,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"213的表现\"}}]},{\"name\":\"员工22\",\"birthday\":\"2022-03-12T00:00:00.000+08:00\",\"gender\":\"女\",\"email\":\"员工22的email\",\"identifyNumber\":\"22\",\"accountNumber\":22,\"password\":\"123\",\"experiences\":[{\"accountNumber\":22,\"companyId\":3,\"departmentId\":1,\"positionId\":1,\"jobNumber\":32211,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":22,\"companyId\":3,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"223的表现\"}}]},{\"name\":\"员工23\",\"birthday\":\"2023-03-12T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"员工23的email\",\"identifyNumber\":\"23\",\"accountNumber\":23,\"password\":\"123\",\"experiences\":[{\"accountNumber\":23,\"companyId\":3,\"departmentId\":1,\"positionId\":1,\"jobNumber\":32311,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":23,\"companyId\":3,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"233的表现\"}}]},{\"name\":\"跳槽哥\",\"birthday\":\"2000-01-01T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2875233439@qq.com\",\"identifyNumber\":\"25\",\"accountNumber\":25,\"password\":\"123\",\"experiences\":[{\"accountNumber\":25,\"companyId\":3,\"departmentId\":2,\"positionId\":1,\"jobNumber\":32521,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":25,\"companyId\":3,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"253的表现\"}}]}]")))
                .andDo(MockMvcResultHandlers.print());

        mvc.perform(MockMvcRequestBuilders.get("/getPresentEmployeesPage.do")
                .param("page","4")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[]")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    public void getEmployeeByCompanyIdAndNamePageTest() throws Exception{
        DataBaseUtilPages.getInstance().initEmployee(employeeRepository);
        DataBaseUtilPages.getInstance().initExperience(experienceRepository);
        DataBaseUtilPages.getInstance().initAssessment(assessmentRepository);

        mvc.perform(MockMvcRequestBuilders.get("/getEmployeeByCompanyIdAndNamePage.do")
                .param("name","工").param("page","3")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"name\":\"员工20\",\"birthday\":\"2020-03-12T00:00:00.000+08:00\",\"gender\":\"女\",\"email\":\"员工20的email\",\"identifyNumber\":\"20\",\"accountNumber\":20,\"password\":\"123\",\"experiences\":[{\"accountNumber\":20,\"companyId\":3,\"departmentId\":1,\"positionId\":1,\"jobNumber\":32011,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":20,\"companyId\":3,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"203的表现\"}}]},{\"name\":\"员工21\",\"birthday\":\"2021-03-12T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"员工21的email\",\"identifyNumber\":\"21\",\"accountNumber\":21,\"password\":\"123\",\"experiences\":[{\"accountNumber\":21,\"companyId\":3,\"departmentId\":1,\"positionId\":1,\"jobNumber\":32111,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":21,\"companyId\":3,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"213的表现\"}}]},{\"name\":\"员工22\",\"birthday\":\"2022-03-12T00:00:00.000+08:00\",\"gender\":\"女\",\"email\":\"员工22的email\",\"identifyNumber\":\"22\",\"accountNumber\":22,\"password\":\"123\",\"experiences\":[{\"accountNumber\":22,\"companyId\":3,\"departmentId\":1,\"positionId\":1,\"jobNumber\":32211,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":22,\"companyId\":3,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"223的表现\"}}]},{\"name\":\"员工23\",\"birthday\":\"2023-03-12T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"员工23的email\",\"identifyNumber\":\"23\",\"accountNumber\":23,\"password\":\"123\",\"experiences\":[{\"accountNumber\":23,\"companyId\":3,\"departmentId\":1,\"positionId\":1,\"jobNumber\":32311,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":23,\"companyId\":3,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"233的表现\"}}]}]")))
                .andDo(MockMvcResultHandlers.print());

        mvc.perform(MockMvcRequestBuilders.get("/getEmployeeByCompanyIdAndNamePage.do")
                .param("name","工").param("page","4")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[]")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    public void getEmployeeByCompanyIdAndGenderPageTest() throws Exception{
        DataBaseUtilPages.getInstance().initEmployee(employeeRepository);
        DataBaseUtilPages.getInstance().initExperience(experienceRepository);
        DataBaseUtilPages.getInstance().initAssessment(assessmentRepository);

        mvc.perform(MockMvcRequestBuilders.get("/getEmployeeByCompanyIdAndGenderPage.do")
                .param("gender","男").param("page","2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"name\":\"员工21\",\"birthday\":\"2021-03-12T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"员工21的email\",\"identifyNumber\":\"21\",\"accountNumber\":21,\"password\":\"123\",\"experiences\":[{\"accountNumber\":21,\"companyId\":3,\"departmentId\":1,\"positionId\":1,\"jobNumber\":32111,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":21,\"companyId\":3,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"213的表现\"}}]},{\"name\":\"员工23\",\"birthday\":\"2023-03-12T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"员工23的email\",\"identifyNumber\":\"23\",\"accountNumber\":23,\"password\":\"123\",\"experiences\":[{\"accountNumber\":23,\"companyId\":3,\"departmentId\":1,\"positionId\":1,\"jobNumber\":32311,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":23,\"companyId\":3,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"233的表现\"}}]},{\"name\":\"跳槽哥\",\"birthday\":\"2000-01-01T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2875233439@qq.com\",\"identifyNumber\":\"25\",\"accountNumber\":25,\"password\":\"123\",\"experiences\":[{\"accountNumber\":25,\"companyId\":3,\"departmentId\":2,\"positionId\":1,\"jobNumber\":32521,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":25,\"companyId\":3,\"startTime\":\"2077-03-12T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"253的表现\"}}]}]")))
                .andDo(MockMvcResultHandlers.print());

        mvc.perform(MockMvcRequestBuilders.get("/getEmployeeByCompanyIdAndGenderPage.do")
                .param("gender","男").param("page","3")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[]")))
                .andDo(MockMvcResultHandlers.print());
    }
}
