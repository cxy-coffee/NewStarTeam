package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class EmailServiceImpTest extends BaseTest {
    @Autowired
    private EmailService emailService;

    @Test
    public void sendSimpleEmailTest() throws Exception{
        String to = "2019302110260@whu.edu.cn";
        String subject = "测试SpringBoot发送简单邮件";
        String content = "测试SpringBoot发送简单邮件的内容";
        emailService.sendSimpleEmail(to,subject,content);
    }

}