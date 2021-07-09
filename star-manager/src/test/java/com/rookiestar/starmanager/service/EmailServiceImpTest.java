package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class EmailServiceImpTest extends BaseTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EmailService emailService;

    @Test
    public void sendSimpleEmailTest() throws Exception{
        String to = "2019302110260@whu.edu.cn";
        String subject = "测试SpringBoot发送简单邮件";
        String content = "测试SpringBoot发送简单邮件的内容";
        emailService.sendSimpleEmail(to,subject,content);
    }

    @Test
    public void generateVerificationCodeTest(){
        for (int i = 0; i < 10; i++) {
            String code = emailService.generateVerificationCode();
            logger.info(code);
            Assert.assertEquals(code.length(),6);
        }
    }

}