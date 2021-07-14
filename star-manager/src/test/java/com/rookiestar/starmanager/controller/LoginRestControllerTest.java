package com.rookiestar.starmanager.controller;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.repository.CompanyToReviewRepository;
import com.rookiestar.starmanager.util.DataBaseUtil;
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

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class that test LoginRestController
 *
 * @author 曹向阳
 * @date 2021/7/14
 */
public class LoginRestControllerTest extends BaseTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;

    @Autowired
    private CompanyToReviewRepository companyToReviewRepository;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    @Transactional
    public void registerCompanyTest()throws Exception{
        DataBaseUtil.getInstance().initCompanyToReview(companyToReviewRepository);
//        String name, String legalRepresentativeName, String email, String address, String phone
        mvc.perform(MockMvcRequestBuilders.get("/registerCompany.do?name=腾讯&legalRepresentativeName=马化腾&email=abcdef@qq.com&address=广东省深圳市南山区&phone=1342525262")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("{\"companyId\":3,\"name\":\"腾讯\",\"legalRepresentativeName\":\"马化腾\",\"email\":\"abcdef@qq.com\",\"address\":\"广东省深圳市南山区\",\"phone\":\"1342525262\"}")))
                .andDo(MockMvcResultHandlers.print());
    }

}