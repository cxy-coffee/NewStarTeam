package com.rookiestar.starmanager.controller;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.assessment.Assessment;
import com.rookiestar.starmanager.entity.company.Company;
import com.rookiestar.starmanager.entity.company.CompanyToReview;
import com.rookiestar.starmanager.entity.department.Department;
import com.rookiestar.starmanager.entity.employee.Employee;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

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
    public void updateEmployeeTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        Employee employee=new Employee("测试名字", DateUtil.parse("2001-01-20"),"男","199","5",5,"123",null);
        Employee actualEmployee=DataBaseUtil.getInstance().getEmployeeMap().get(5);
        employee.setExperiences(experienceRepository.findAllByAccountNumber(5));
        Assert.assertNotEquals(employee,actualEmployee);
        mvc.perform(MockMvcRequestBuilders.get("/updateEmployee.do?name=测试名字&birthday=2001-01-20&gender=男&email=199&identifyNumber=5&accountNumber=5&password=123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("true")))
                .andDo(MockMvcResultHandlers.print());
        actualEmployee=retrieveService.retrieveEmployeeByIdentifyNumber("5");
        Assert.assertEquals(actualEmployee,employee);
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
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("{\"name\":\"张三\",\"birthday\":\"2000-01-10T00:00:00.000+08:00\",\"gender\":\"男\",\"email\":\"2019302110260@whu.edu.cn\",\"identifyNumber\":\"5\",\"accountNumber\":5,\"password\":\"123\",\"experiences\":[{\"accountNumber\":5,\"companyId\":1,\"departmentId\":2,\"positionId\":1,\"jobNumber\":1521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":false,\"assessment\":null},{\"accountNumber\":5,\"companyId\":2,\"departmentId\":2,\"positionId\":1,\"jobNumber\":2521,\"startTime\":\"2010-01-10T00:00:00.000+08:00\",\"endTime\":null,\"isEnd\":true,\"assessment\":null}]}")))
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