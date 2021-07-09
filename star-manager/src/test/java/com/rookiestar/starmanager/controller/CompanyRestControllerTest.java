package com.rookiestar.starmanager.controller;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.repository.EmployeeRepository;
import com.rookiestar.starmanager.repository.ExperienceRepository;
import com.rookiestar.starmanager.util.DataBaseUtil;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;


public class CompanyRestControllerTest extends BaseTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;
    private MockHttpSession session;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ExperienceRepository experienceRepository;

    @Before
    public void setUp() throws Exception{
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();

        session = new MockHttpSession();
        session.setAttribute("company", DataBaseUtil.getInstance().getCompanyMap().get(1));
    }

    @Test
    @Transactional
    public void getAllEmployeesTest() throws Exception{
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        DataBaseUtil.getInstance().initEmployee(employeeRepository);

        mvc.perform(MockMvcRequestBuilders.get("/getAllEmployees.do")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"name\":\"张三\",\"birthday\":\"2000-01-09T16:00:00.000+00:00\",\"gender\":\"男\",\"email\":\"1025405845@qq.com\",\"accountNumber\":5,\"password\":\"123\",\"experiences\":[{\"accountNumber\":5,\"companyId\":1,\"jobNumber\":15,\"startTime\":\"2010-01-09T16:00:00.000+00:00\",\"endTime\":null,\"end\":false},{\"accountNumber\":5,\"companyId\":2,\"jobNumber\":25,\"startTime\":\"2010-01-09T16:00:00.000+00:00\",\"endTime\":null,\"end\":true}]},{\"name\":\"李四\",\"birthday\":\"2001-01-10T16:00:00.000+00:00\",\"gender\":\"女\",\"email\":\"李四邮箱\",\"accountNumber\":6,\"password\":\"456\",\"experiences\":[{\"accountNumber\":6,\"companyId\":1,\"jobNumber\":16,\"startTime\":\"2011-01-10T16:00:00.000+00:00\",\"endTime\":null,\"end\":false},{\"accountNumber\":6,\"companyId\":2,\"jobNumber\":26,\"startTime\":\"2011-01-10T16:00:00.000+00:00\",\"endTime\":null,\"end\":true}]},{\"name\":\"王五\",\"birthday\":\"2002-01-11T16:00:00.000+00:00\",\"gender\":\"男\",\"email\":\"王五邮箱\",\"accountNumber\":7,\"password\":\"123\",\"experiences\":[{\"accountNumber\":7,\"companyId\":1,\"jobNumber\":17,\"startTime\":\"2012-01-11T16:00:00.000+00:00\",\"endTime\":null,\"end\":true},{\"accountNumber\":7,\"companyId\":2,\"jobNumber\":27,\"startTime\":\"2012-01-11T16:00:00.000+00:00\",\"endTime\":null,\"end\":false}]},{\"name\":\"赵六\",\"birthday\":\"2003-01-12T16:00:00.000+00:00\",\"gender\":\"女\",\"email\":\"赵六邮箱\",\"accountNumber\":8,\"password\":\"456\",\"experiences\":[{\"accountNumber\":8,\"companyId\":1,\"jobNumber\":18,\"startTime\":\"2013-01-12T16:00:00.000+00:00\",\"endTime\":null,\"end\":true},{\"accountNumber\":8,\"companyId\":2,\"jobNumber\":28,\"startTime\":\"2013-01-12T16:00:00.000+00:00\",\"endTime\":null,\"end\":false}]}]")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    public void getPresentEmployeesTest() throws Exception{
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        DataBaseUtil.getInstance().initEmployee(employeeRepository);

        mvc.perform(MockMvcRequestBuilders.get("/getPresentEmployees.do")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"name\":\"张三\",\"birthday\":\"2000-01-09T16:00:00.000+00:00\",\"gender\":\"男\",\"email\":\"1025405845@qq.com\",\"accountNumber\":5,\"password\":\"123\",\"experiences\":[{\"accountNumber\":5,\"companyId\":1,\"jobNumber\":15,\"startTime\":\"2010-01-09T16:00:00.000+00:00\",\"endTime\":null,\"end\":false},{\"accountNumber\":5,\"companyId\":2,\"jobNumber\":25,\"startTime\":\"2010-01-09T16:00:00.000+00:00\",\"endTime\":null,\"end\":true}]},{\"name\":\"李四\",\"birthday\":\"2001-01-10T16:00:00.000+00:00\",\"gender\":\"女\",\"email\":\"李四邮箱\",\"accountNumber\":6,\"password\":\"456\",\"experiences\":[{\"accountNumber\":6,\"companyId\":1,\"jobNumber\":16,\"startTime\":\"2011-01-10T16:00:00.000+00:00\",\"endTime\":null,\"end\":false},{\"accountNumber\":6,\"companyId\":2,\"jobNumber\":26,\"startTime\":\"2011-01-10T16:00:00.000+00:00\",\"endTime\":null,\"end\":true}]}]")))
                .andDo(MockMvcResultHandlers.print());
    }
}