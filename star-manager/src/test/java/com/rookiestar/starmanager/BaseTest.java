package com.rookiestar.starmanager;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Test class that is the base of other test class
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BaseTest {
    @Before
    public void init(){
        System.out.println("————————————测试开始————————————");
    }
    @After
    public void after(){
        System.out.println("————————————测试结束————————————");
    }
}
