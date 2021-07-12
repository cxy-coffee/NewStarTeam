package com.rookiestar.starmanager.controller;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.Employee;
import com.rookiestar.starmanager.entity.Experience;
import com.rookiestar.starmanager.repository.EmployeeRepository;
import com.rookiestar.starmanager.repository.ExperienceRepository;
import com.rookiestar.starmanager.util.DataBaseUtil;
import com.rookiestar.starmanager.util.DateUtil;
import org.hamcrest.Matchers;
import org.junit.Assert;
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

import java.util.Date;

/**
 * Test class that test CompanyRestController
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
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
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"name\":\"张三\",\"birthday\":\"2000-01-10T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2019302110260@whu.edu.cn\",\"identifyNumber\":\"5\",\"accountNumber\":5,\"password\":\"123\",\"experiences\":[{\"accountNumber\":5,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":null},{\"accountNumber\":5,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":null}]},{\"name\":\"李四\",\"birthday\":\"2001-01-11T00:00:00.000+08:00\",\"gender\":\"女\",\"email\":\"2019302110261@whu.edu.cn\",\"identifyNumber\":\"6\",\"accountNumber\":6,\"password\":\"456\",\"experiences\":[{\"accountNumber\":6,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1621,\"startTime\":\"2011-01-11T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":null},{\"accountNumber\":6,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2621,\"startTime\":\"2011-01-11T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":null}]},{\"name\":\"王五\",\"birthday\":\"2002-01-12T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2019302110262@whu.edu.cn\",\"identifyNumber\":\"7\",\"accountNumber\":7,\"password\":\"123\",\"experiences\":[{\"accountNumber\":7,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1721,\"startTime\":\"2012-01-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":null},{\"accountNumber\":7,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2721,\"startTime\":\"2012-01-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":null}]},{\"name\":\"赵六\",\"birthday\":\"2003-01-13T00:00:00.000+08:00\",\"gender\":\"女\",\"email\":\"2019302110263@whu.edu.cn\",\"identifyNumber\":\"8\",\"accountNumber\":8,\"password\":\"456\",\"experiences\":[{\"accountNumber\":8,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1821,\"startTime\":\"2013-01-13T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":null},{\"accountNumber\":8,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2821,\"startTime\":\"2013-01-13T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":null}]}]")))
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
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"name\":\"张三\",\"birthday\":\"2000-01-10T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2019302110260@whu.edu.cn\",\"identifyNumber\":\"5\",\"accountNumber\":5,\"password\":\"123\",\"experiences\":[{\"accountNumber\":5,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":null},{\"accountNumber\":5,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":null}]},{\"name\":\"李四\",\"birthday\":\"2001-01-11T00:00:00.000+08:00\",\"gender\":\"女\",\"email\":\"2019302110261@whu.edu.cn\",\"identifyNumber\":\"6\",\"accountNumber\":6,\"password\":\"456\",\"experiences\":[{\"accountNumber\":6,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1621,\"startTime\":\"2011-01-11T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":null},{\"accountNumber\":6,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2621,\"startTime\":\"2011-01-11T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":null}]}]")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    public void sendEmailCodeTest() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/sendEmailCode.do?to=2019302110260@whu.edu.cn")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("verificationCode").isNumber())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    public void hireEmployeeTest() throws Exception{
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        mvc.perform(MockMvcRequestBuilders.get("/hireEmployee.do?accountNumber=9&departmentId=1&positionId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("accountNumber").value(9))
                .andExpect(MockMvcResultMatchers.jsonPath("companyId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("departmentId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("positionId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("jobNumber").value(9111))
                .andExpect(MockMvcResultMatchers.jsonPath("isEnd").value(false))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    public void registerEmployeeTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        mvc.perform(MockMvcRequestBuilders.get("/registerEmployee.do?name=测试名字&birthday=2001-01-20&gender=男&email=199&identifyNumber=991&password=123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("{\"name\":\"测试名字\",\"birthday\":\"2001-01-20T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"199\",\"identifyNumber\":\"991\",\"accountNumber\":9,\"password\":\"123\",\"experiences\":null}")))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    @Transactional
    public void getEmployeesByNameTest() throws Exception{
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        mvc.perform(MockMvcRequestBuilders.get("/getEmployeesByName.do?name=张")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"name\":\"张三\",\"birthday\":\"2000-01-10T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2019302110260@whu.edu.cn\",\"identifyNumber\":\"5\",\"accountNumber\":5,\"password\":\"123\",\"experiences\":[{\"accountNumber\":5,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":null},{\"accountNumber\":5,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":null}]}]")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    public void getEmployeeByIdentifyNumberTest()throws Exception{
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        mvc.perform(MockMvcRequestBuilders.get("/getEmployeeByIdentifyNumber.do?identifyNumber=5")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("{\"name\":\"张三\",\"birthday\":\"2000-01-10T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2019302110260@whu.edu.cn\",\"identifyNumber\":\"5\",\"accountNumber\":5,\"password\":\"123\",\"experiences\":[{\"accountNumber\":5,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":null},{\"accountNumber\":5,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":null}]}")))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    @Transactional
    public void getEmployeesByGenderTest()throws Exception{
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        mvc.perform(MockMvcRequestBuilders.get("/getEmployeesByGender.do?gender=男")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"name\":\"张三\",\"birthday\":\"2000-01-10T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2019302110260@whu.edu.cn\",\"identifyNumber\":\"5\",\"accountNumber\":5,\"password\":\"123\",\"experiences\":[{\"accountNumber\":5,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":null},{\"accountNumber\":5,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":null}]},{\"name\":\"王五\",\"birthday\":\"2002-01-12T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2019302110262@whu.edu.cn\",\"identifyNumber\":\"7\",\"accountNumber\":7,\"password\":\"123\",\"experiences\":[{\"accountNumber\":7,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1721,\"startTime\":\"2012-01-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":null},{\"accountNumber\":7,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2721,\"startTime\":\"2012-01-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":null}]}]")))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    @Transactional
    public void updateEmployeeTest()throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        Employee employee=new Employee("测试名字", DateUtil.parse("2001-01-20"),"男","199","5",5,"123",null);

        mvc.perform(MockMvcRequestBuilders.get("/updateEmployee.do?name=测试名字&birthday=2001-01-20&gender=男&email=199&identifyNumber=5&accountNumber=5&password=123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        Employee actualEmployee = DataBaseUtil.getInstance().getEmployeeMap().get(5);
        Assert.assertEquals(actualEmployee,employee);

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