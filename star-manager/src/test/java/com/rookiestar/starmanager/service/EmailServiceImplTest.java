package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class that test EmailServiceImpl
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
public class EmailServiceImplTest extends BaseTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EmailService emailService;

    //发送验证码邮件测试
    @Test
    public void sendSimpleEmailTest() throws Exception{
        String to = "2019302110260@whu.edu.cn";
        String subject = "测试SpringBoot发送简单邮件";
        String content = "测试SpringBoot发送简单邮件的内容";
        emailService.sendSimpleEmail(to,subject,content);
    }

    //动态生成验证码测试
    @Test
    public void generateVerificationCodeTest(){
        for (int i = 0; i < 10; i++) {
            String code = emailService.generateVerificationCode();
            logger.info(code);
            Assert.assertEquals(code.length(),6);
        }
    }

}