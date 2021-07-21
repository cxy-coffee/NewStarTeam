package com.rookiestar.starmanager.controller;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.constant.AttributeNames;
import com.rookiestar.starmanager.entity.assessment.Assessment;
import com.rookiestar.starmanager.entity.department.Department;
import com.rookiestar.starmanager.entity.experience.Experience;
import com.rookiestar.starmanager.entity.position.Position;
import com.rookiestar.starmanager.repository.*;
import com.rookiestar.starmanager.service.RetrieveService;
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
 * Test class that test CompanyRestController
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
@SuppressWarnings("deprecation")
public class CompanyRestControllerTest extends BaseTest {
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
    @Autowired
    private JobHuntingRepository jobHuntingRepository;
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
                .param("companyId","1").param("jobNumber","1521").param("password","234").session(session));
        resultActions3.andExpect(MockMvcResultMatchers.status().isOk());
        session = (MockHttpSession) resultActions3.andReturn().getRequest().getSession();
        assert session != null;
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
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"name\":\"张三\",\"birthday\":\"2000-01-10T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2019302110260@whu.edu.cn\",\"identifyNumber\":\"5\",\"accountNumber\":5,\"password\":\"123\",\"experiences\":[{\"accountNumber\":5,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":5,\"companyId\":1,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"51的表现\"}},{\"accountNumber\":5,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":{\"accountNumber\":5,\"companyId\":2,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"52的表现\"}}]},{\"name\":\"李四\",\"birthday\":\"2001-01-11T00:00:00.000+08:00\",\"gender\":\"女\",\"email\":\"2019302110261@whu.edu.cn\",\"identifyNumber\":\"6\",\"accountNumber\":6,\"password\":\"456\",\"experiences\":[{\"accountNumber\":6,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1621,\"startTime\":\"2011-01-11T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":6,\"companyId\":1,\"startTime\":\"2011-01-11T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"61的表现\"}},{\"accountNumber\":6,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2621,\"startTime\":\"2011-01-11T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":{\"accountNumber\":6,\"companyId\":2,\"startTime\":\"2011-01-11T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"62的表现\"}}]},{\"name\":\"王五\",\"birthday\":\"2002-01-12T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2019302110262@whu.edu.cn\",\"identifyNumber\":\"7\",\"accountNumber\":7,\"password\":\"123\",\"experiences\":[{\"accountNumber\":7,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1721,\"startTime\":\"2012-01-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":{\"accountNumber\":7,\"companyId\":1,\"startTime\":\"2012-01-12T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"71的表现\"}},{\"accountNumber\":7,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2721,\"startTime\":\"2012-01-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":7,\"companyId\":2,\"startTime\":\"2012-01-12T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"72的表现\"}}]},{\"name\":\"赵六\",\"birthday\":\"2003-01-13T00:00:00.000+08:00\",\"gender\":\"女\",\"email\":\"2019302110263@whu.edu.cn\",\"identifyNumber\":\"8\",\"accountNumber\":8,\"password\":\"456\",\"experiences\":[{\"accountNumber\":8,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1821,\"startTime\":\"2013-01-13T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":{\"accountNumber\":8,\"companyId\":1,\"startTime\":\"2013-01-13T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"81的表现\"}},{\"accountNumber\":8,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2821,\"startTime\":\"2013-01-13T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":8,\"companyId\":2,\"startTime\":\"2013-01-13T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"82的表现\"}}]}]")))
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
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"name\":\"张三\",\"birthday\":\"2000-01-10T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2019302110260@whu.edu.cn\",\"identifyNumber\":\"5\",\"accountNumber\":5,\"password\":\"123\",\"experiences\":[{\"accountNumber\":5,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":5,\"companyId\":1,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"51的表现\"}},{\"accountNumber\":5,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":{\"accountNumber\":5,\"companyId\":2,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"52的表现\"}}]},{\"name\":\"李四\",\"birthday\":\"2001-01-11T00:00:00.000+08:00\",\"gender\":\"女\",\"email\":\"2019302110261@whu.edu.cn\",\"identifyNumber\":\"6\",\"accountNumber\":6,\"password\":\"456\",\"experiences\":[{\"accountNumber\":6,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1621,\"startTime\":\"2011-01-11T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":6,\"companyId\":1,\"startTime\":\"2011-01-11T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"61的表现\"}},{\"accountNumber\":6,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2621,\"startTime\":\"2011-01-11T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":{\"accountNumber\":6,\"companyId\":2,\"startTime\":\"2011-01-11T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"62的表现\"}}]}]")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    public void hireEmployeeTest() throws Exception{
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        mvc.perform(MockMvcRequestBuilders.get("/hireEmployee.do?accountNumber=7&departmentId=1&positionId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("accountNumber").value(7))
                .andExpect(MockMvcResultMatchers.jsonPath("companyId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("departmentId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("positionId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("jobNumber").value(7111))
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
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"name\":\"张三\",\"birthday\":\"2000-01-10T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2019302110260@whu.edu.cn\",\"identifyNumber\":\"5\",\"accountNumber\":5,\"password\":\"123\",\"experiences\":[{\"accountNumber\":5,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":5,\"companyId\":1,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"51的表现\"}},{\"accountNumber\":5,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":{\"accountNumber\":5,\"companyId\":2,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"52的表现\"}}]}]")))
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
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("{\"name\":\"张三\",\"birthday\":\"2000-01-10T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2019302110260@whu.edu.cn\",\"identifyNumber\":\"5\",\"accountNumber\":5,\"password\":\"123\",\"experiences\":[{\"accountNumber\":5,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":5,\"companyId\":1,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"51的表现\"}},{\"accountNumber\":5,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":{\"accountNumber\":5,\"companyId\":2,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"52的表现\"}}]}")))
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
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"name\":\"张三\",\"birthday\":\"2000-01-10T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2019302110260@whu.edu.cn\",\"identifyNumber\":\"5\",\"accountNumber\":5,\"password\":\"123\",\"experiences\":[{\"accountNumber\":5,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":5,\"companyId\":1,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"51的表现\"}},{\"accountNumber\":5,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":{\"accountNumber\":5,\"companyId\":2,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"52的表现\"}}]},{\"name\":\"王五\",\"birthday\":\"2002-01-12T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2019302110262@whu.edu.cn\",\"identifyNumber\":\"7\",\"accountNumber\":7,\"password\":\"123\",\"experiences\":[{\"accountNumber\":7,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1721,\"startTime\":\"2012-01-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":{\"accountNumber\":7,\"companyId\":1,\"startTime\":\"2012-01-12T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"71的表现\"}},{\"accountNumber\":7,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2721,\"startTime\":\"2012-01-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":7,\"companyId\":2,\"startTime\":\"2012-01-12T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"72的表现\"}}]}]")))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    @Transactional
    public void getCompanyByCompanyIdTest()throws Exception{
        DataBaseUtil.getInstance().initCompany(companyRepository);
        mvc.perform(MockMvcRequestBuilders.get("/getCompanyByCompanyId.do?companyId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("{\"companyId\":1,\"name\":\"我的公司1\",\"legalRepresentativeName\":\"Alan\",\"email\":\"lihaoc@whu.edu.cn\",\"address\":\"湖北省武汉市洪山区\",\"phone\":\"88555273\",\"experiences\":[{\"accountNumber\":5,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":5,\"companyId\":1,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"51的表现\"}},{\"accountNumber\":6,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1621,\"startTime\":\"2011-01-11T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":6,\"companyId\":1,\"startTime\":\"2011-01-11T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"61的表现\"}},{\"accountNumber\":7,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1721,\"startTime\":\"2012-01-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":{\"accountNumber\":7,\"companyId\":1,\"startTime\":\"2012-01-12T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"71的表现\"}},{\"accountNumber\":8,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1821,\"startTime\":\"2013-01-13T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":{\"accountNumber\":8,\"companyId\":1,\"startTime\":\"2013-01-13T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"81的表现\"}}],\"departments\":[{\"companyId\":1,\"departmentId\":1,\"name\":\"公司1部门1\",\"positions\":[{\"companyId\":1,\"departmentId\":1,\"positionId\":1,\"name\":\"公司1部门1员工\"},{\"companyId\":1,\"departmentId\":1,\"positionId\":2,\"name\":\"公司1部门1经理\"}]},{\"companyId\":1,\"departmentId\":2,\"name\":\"公司1部门2\",\"positions\":[{\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"name\":\"公司1部门2员工\"},{\"companyId\":1,\"departmentId\":2,\"positionId\":2,\"name\":\"公司1部门2经理\"}]}]}")))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    @Transactional
    public void updateAssessmentTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        DataBaseUtil.getInstance().initAssessment(assessmentRepository);

        Assessment assessment = new Assessment(5, 1, DateUtil.parse("2010-01-10"), "10/10", "51的表现修改后");
        Assessment actualAssessment=DataBaseUtil.getInstance().getAssessmentMap().get(51);
        Assert.assertNotEquals(assessment,actualAssessment);
        mvc.perform(MockMvcRequestBuilders.get("/updateAssessment.do?accountNumber=5&companyId=1&startTime=2010-01-10&absenteeismRate=10/10&performance=51的表现修改后")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("true")))
                .andDo(MockMvcResultHandlers.print());
        actualAssessment=assessmentRepository.findByAccountNumberAndCompanyIdAndStartTime(5,1,DateUtil.parse("2010-01-10"));
        Assert.assertEquals(assessment,actualAssessment);
    }

    @Test
    @Transactional
    public void updateExperienceTest() throws Exception{
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        Experience experience=new Experience(5,1,4396,7777,1521,DateUtil.parse("2010-01-10"),DateUtil.parse("2021-10-15"),true);
        experience.setAssessment(assessmentRepository.findByAccountNumberAndCompanyIdAndStartTime(experience.getAccountNumber(),experience.getCompanyId(),experience.getStartTime()));
        Experience actualExperience=retrieveService.retrieveExperienceByAccountNumberAndCompanyIdAndStartTime(5,1,DateUtil.parse("2010-01-10"));
        Assert.assertNotEquals(experience,actualExperience);
//        int accountNumber,int companyId,
//        int departmentId,int positionId,int jobNumber,
//        String startTime,String endTime,boolean isEnd
        mvc.perform(MockMvcRequestBuilders.get("/updateExperience.do?accountNumber=5&companyId=1" +
                "&departmentId=4396&positionId=7777&jobNumber=1521" +
                "&startTime=2010-01-10&endTime=2021-10-15&isEnd=true")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("true")))
                .andDo(MockMvcResultHandlers.print());
        actualExperience=retrieveService.retrieveExperienceByAccountNumberAndCompanyIdAndStartTime(5,1,DateUtil.parse("2010-01-10"));
        Assert.assertEquals(experience,actualExperience);
    }

    @Test
    @Transactional
    public void getEmployeeByEmailTest()throws Exception{
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        mvc.perform(MockMvcRequestBuilders.get("/getEmployeeByEmail.do?email=2019302110260@whu.edu.cn")
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
    public void registerCompanyTest()throws Exception{
        DataBaseUtil.getInstance().initCompanyToReview(companyToReviewRepository);
//        String name, String legalRepresentativeName, String email, String address, String phone
        mvc.perform(MockMvcRequestBuilders.get("/registerCompany.do?name=腾讯&legalRepresentativeName=马化腾&email=abcdef@qq.com&address=广东省深圳市南山区&phone=1342525262")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("{\"companyId\":3,\"name\":\"腾讯\",\"legalRepresentativeName\":\"马化腾\",\"email\":\"abcdef@qq.com\",\"address\":\"广东省深圳市南山区\",\"phone\":\"1342525262\"}")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    public void updateDepartmentTest() throws Exception{
        DataBaseUtil.getInstance().initDepartment(departmentRepository);
        Department department=new Department(1,1,"公司1部门12314",positionRepository.findByCompanyIdAndDepartmentId(1,1));
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

    @Test
    @Transactional
    public void deleteDepartmentTest() throws Exception{
        DataBaseUtil.getInstance().initDepartment(departmentRepository);
        Department department = retrieveService.retrieveDepartmentByCompanyIdAndDepartmentId(1,1);
        Assert.assertNotNull(department);
        mvc.perform(MockMvcRequestBuilders.get("/deleteDepartment.do?companyId=1&departmentId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("true")))
                .andDo(MockMvcResultHandlers.print());
        department=retrieveService.retrieveDepartmentByCompanyIdAndDepartmentId(1,1);
        Assert.assertNull(department);
    }

    @Test
    @Transactional
    public void deletePositionTest()throws Exception{
        DataBaseUtil.getInstance().initPosition(positionRepository);
        Position position=retrieveService.retrievePositionByCompanyIdAndDepartmentIdAndPositionId(1,1,1);
        Assert.assertNotNull(position);
        mvc.perform(MockMvcRequestBuilders.get("/deletePosition.do?companyId=1&departmentId=1&positionId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("true")))
                .andDo(MockMvcResultHandlers.print());
        position=retrieveService.retrievePositionByCompanyIdAndDepartmentIdAndPositionId(1,1,1);
        Assert.assertNull(position);
    }

    @Test
    @Transactional
    public void getDepartmentByCompanyIdTest()throws Exception{
        DataBaseUtil.getInstance().initDepartment(departmentRepository);
        mvc.perform(MockMvcRequestBuilders.get("/getDepartmentByCompanyId.do?companyId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"companyId\":1,\"departmentId\":1,\"name\":\"公司1部门1\",\"positions\":[{\"companyId\":1,\"departmentId\":1,\"positionId\":1,\"name\":\"公司1部门1员工\"},{\"companyId\":1,\"departmentId\":1,\"positionId\":2,\"name\":\"公司1部门1经理\"}]},{\"companyId\":1,\"departmentId\":2,\"name\":\"公司1部门2\",\"positions\":[{\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"name\":\"公司1部门2员工\"},{\"companyId\":1,\"departmentId\":2,\"positionId\":2,\"name\":\"公司1部门2经理\"}]}]")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    public void getPositionByCompanyIdAndDepartmentIdTest()throws Exception{
        DataBaseUtil.getInstance().initPosition(positionRepository);
        mvc.perform(MockMvcRequestBuilders.get("/getPositionByCompanyIdAndDepartmentId.do?companyId=1&departmentId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"companyId\":1,\"departmentId\":1,\"positionId\":1,\"name\":\"公司1部门1员工\"},{\"companyId\":1,\"departmentId\":1,\"positionId\":2,\"name\":\"公司1部门1经理\"}]")))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    @Transactional
    public void getEmployeeByCompanyIdAndDepartmentIdTest()throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        mvc.perform(MockMvcRequestBuilders.get("/getEmployeeByCompanyIdAndDepartmentId.do?companyId=1&departmentId=2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"name\":\"张三\",\"birthday\":\"2000-01-10T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2019302110260@whu.edu.cn\",\"identifyNumber\":\"5\",\"accountNumber\":5,\"password\":\"123\",\"experiences\":[{\"accountNumber\":5,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":5,\"companyId\":1,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"51的表现\"}},{\"accountNumber\":5,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":{\"accountNumber\":5,\"companyId\":2,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"52的表现\"}}]},{\"name\":\"李四\",\"birthday\":\"2001-01-11T00:00:00.000+08:00\",\"gender\":\"女\",\"email\":\"2019302110261@whu.edu.cn\",\"identifyNumber\":\"6\",\"accountNumber\":6,\"password\":\"456\",\"experiences\":[{\"accountNumber\":6,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1621,\"startTime\":\"2011-01-11T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":6,\"companyId\":1,\"startTime\":\"2011-01-11T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"61的表现\"}},{\"accountNumber\":6,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2621,\"startTime\":\"2011-01-11T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":{\"accountNumber\":6,\"companyId\":2,\"startTime\":\"2011-01-11T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"62的表现\"}}]},{\"name\":\"王五\",\"birthday\":\"2002-01-12T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2019302110262@whu.edu.cn\",\"identifyNumber\":\"7\",\"accountNumber\":7,\"password\":\"123\",\"experiences\":[{\"accountNumber\":7,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1721,\"startTime\":\"2012-01-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":{\"accountNumber\":7,\"companyId\":1,\"startTime\":\"2012-01-12T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"71的表现\"}},{\"accountNumber\":7,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2721,\"startTime\":\"2012-01-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":7,\"companyId\":2,\"startTime\":\"2012-01-12T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"72的表现\"}}]},{\"name\":\"赵六\",\"birthday\":\"2003-01-13T00:00:00.000+08:00\",\"gender\":\"女\",\"email\":\"2019302110263@whu.edu.cn\",\"identifyNumber\":\"8\",\"accountNumber\":8,\"password\":\"456\",\"experiences\":[{\"accountNumber\":8,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1821,\"startTime\":\"2013-01-13T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":{\"accountNumber\":8,\"companyId\":1,\"startTime\":\"2013-01-13T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"81的表现\"}},{\"accountNumber\":8,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2821,\"startTime\":\"2013-01-13T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":8,\"companyId\":2,\"startTime\":\"2013-01-13T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"82的表现\"}}]}]")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    public void getEmployeeByCompanyIdAndDepartmentIdAndPositionIdTest()throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        mvc.perform(MockMvcRequestBuilders.get("/getEmployeeByCompanyIdAndDepartmentIdAndPositionId.do?companyId=1&departmentId=2&positionId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"name\":\"张三\",\"birthday\":\"2000-01-10T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2019302110260@whu.edu.cn\",\"identifyNumber\":\"5\",\"accountNumber\":5,\"password\":\"123\",\"experiences\":[{\"accountNumber\":5,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":5,\"companyId\":1,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"51的表现\"}},{\"accountNumber\":5,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":{\"accountNumber\":5,\"companyId\":2,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"52的表现\"}}]},{\"name\":\"李四\",\"birthday\":\"2001-01-11T00:00:00.000+08:00\",\"gender\":\"女\",\"email\":\"2019302110261@whu.edu.cn\",\"identifyNumber\":\"6\",\"accountNumber\":6,\"password\":\"456\",\"experiences\":[{\"accountNumber\":6,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1621,\"startTime\":\"2011-01-11T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":6,\"companyId\":1,\"startTime\":\"2011-01-11T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"61的表现\"}},{\"accountNumber\":6,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2621,\"startTime\":\"2011-01-11T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":{\"accountNumber\":6,\"companyId\":2,\"startTime\":\"2011-01-11T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"62的表现\"}}]},{\"name\":\"王五\",\"birthday\":\"2002-01-12T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2019302110262@whu.edu.cn\",\"identifyNumber\":\"7\",\"accountNumber\":7,\"password\":\"123\",\"experiences\":[{\"accountNumber\":7,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1721,\"startTime\":\"2012-01-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":{\"accountNumber\":7,\"companyId\":1,\"startTime\":\"2012-01-12T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"71的表现\"}},{\"accountNumber\":7,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2721,\"startTime\":\"2012-01-12T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":7,\"companyId\":2,\"startTime\":\"2012-01-12T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"72的表现\"}}]},{\"name\":\"赵六\",\"birthday\":\"2003-01-13T00:00:00.000+08:00\",\"gender\":\"女\",\"email\":\"2019302110263@whu.edu.cn\",\"identifyNumber\":\"8\",\"accountNumber\":8,\"password\":\"456\",\"experiences\":[{\"accountNumber\":8,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1821,\"startTime\":\"2013-01-13T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":{\"accountNumber\":8,\"companyId\":1,\"startTime\":\"2013-01-13T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"81的表现\"}},{\"accountNumber\":8,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2821,\"startTime\":\"2013-01-13T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":8,\"companyId\":2,\"startTime\":\"2013-01-13T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"82的表现\"}}]}]")))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    @Transactional
    public void getEmployeeByCompanyIdAndNameTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        mvc.perform(MockMvcRequestBuilders.get("/getEmployeeByCompanyIdAndName.do?name=张")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"name\":\"张三\",\"birthday\":\"2000-01-10T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2019302110260@whu.edu.cn\",\"identifyNumber\":\"5\",\"accountNumber\":5,\"password\":\"123\",\"experiences\":[{\"accountNumber\":5,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":5,\"companyId\":1,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"51的表现\"}}]}]")))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    @Transactional
    public void getEmployeeByCompanyIdAndGenderTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        mvc.perform(MockMvcRequestBuilders.get("/getEmployeeByCompanyIdAndGender.do?gender=男")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"name\":\"张三\",\"birthday\":\"2000-01-10T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2019302110260@whu.edu.cn\",\"identifyNumber\":\"5\",\"accountNumber\":5,\"password\":\"123\",\"experiences\":[{\"accountNumber\":5,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":5,\"companyId\":1,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"51的表现\"}}]}]")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    public void getEmployeeByCompanyIdAndEmailTest()throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        mvc.perform(MockMvcRequestBuilders.get("/getEmployeeByCompanyIdAndEmail.do?email=2019302110260@whu.edu.cn")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("{\"name\":\"张三\",\"birthday\":\"2000-01-10T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2019302110260@whu.edu.cn\",\"identifyNumber\":\"5\",\"accountNumber\":5,\"password\":\"123\",\"experiences\":[{\"accountNumber\":5,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":5,\"companyId\":1,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"51的表现\"}}]}")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    public void getEmployeeByCompanyIdAndIdentifyNumberTest()throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        mvc.perform(MockMvcRequestBuilders.get("/getEmployeeByCompanyIdAndIdentifyNumber.do?identifyNumber=5")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("{\"name\":\"张三\",\"birthday\":\"2000-01-10T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2019302110260@whu.edu.cn\",\"identifyNumber\":\"5\",\"accountNumber\":5,\"password\":\"123\",\"experiences\":[{\"accountNumber\":5,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":{\"accountNumber\":5,\"companyId\":1,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"absenteeismRate\":\"0/10\",\"performance\":\"51的表现\"}}]}")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    public void getJobHuntingByIdealPositionAndDegreeTest() throws Exception{
        DataBaseUtil.getInstance().initJobHunting(jobHuntingRepository);
        mvc.perform(MockMvcRequestBuilders.get("/getJobHuntingByIdealPositionAndDegree.do?idealPosition=前端工程师&degree=不限")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"degree\":\"高中\",\"jobHunting\":true,\"idealPosition\":\"前端工程师\",\"accountNumber\":7,\"name\":\"王五\",\"email\":\"2019302110262@whu.edu.cn\",\"gender\":\"男\"}]")))
                .andDo(MockMvcResultHandlers.print());
        mvc.perform(MockMvcRequestBuilders.get("/getJobHuntingByIdealPositionAndDegree.do?idealPosition=不限&degree=不限")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"degree\":\"本科\",\"jobHunting\":true,\"idealPosition\":\"设计师\",\"accountNumber\":5,\"name\":\"张三\",\"email\":\"2019302110260@whu.edu.cn\",\"gender\":\"男\"},{\"degree\":\"高中\",\"jobHunting\":true,\"idealPosition\":\"前端工程师\",\"accountNumber\":7,\"name\":\"王五\",\"email\":\"2019302110262@whu.edu.cn\",\"gender\":\"男\"}]")))
                .andDo(MockMvcResultHandlers.print());
        mvc.perform(MockMvcRequestBuilders.get("/getJobHuntingByIdealPositionAndDegree.do?idealPosition=不限&degree=高中")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"degree\":\"高中\",\"jobHunting\":true,\"idealPosition\":\"前端工程师\",\"accountNumber\":7,\"name\":\"王五\",\"email\":\"2019302110262@whu.edu.cn\",\"gender\":\"男\"}]")))
                .andDo(MockMvcResultHandlers.print());
        mvc.perform(MockMvcRequestBuilders.get("/getJobHuntingByIdealPositionAndDegree.do?idealPosition=市场营销&degree=硕士")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[]")))
                .andDo(MockMvcResultHandlers.print());
        mvc.perform(MockMvcRequestBuilders.get("/getJobHuntingByIdealPositionAndDegree.do?idealPosition=前端工程师&degree=高中")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"degree\":\"高中\",\"jobHunting\":true,\"idealPosition\":\"前端工程师\",\"accountNumber\":7,\"name\":\"王五\",\"email\":\"2019302110262@whu.edu.cn\",\"gender\":\"男\"}]")))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    @Transactional
    public void sendInvitationTest() throws Exception{
        DataBaseUtil.getInstance().initJobHunting(jobHuntingRepository);
        mvc.perform(MockMvcRequestBuilders.get("/sendInvitation.do?accountNumber=5")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("true")))
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