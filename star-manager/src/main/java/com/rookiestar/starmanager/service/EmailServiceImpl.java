package com.rookiestar.starmanager.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Service class that handle email service
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
@Service
public class EmailServiceImpl implements EmailService{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${emailService.emailCodeLength}")
    private int emailCodeLength;
    @Autowired
    private JavaMailSender mailSender;
    @Value("${mail.fromMail.addr}")
    private String from;
    @Override
    public void sendSimpleEmail(String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);

        try{
            mailSender.send(simpleMailMessage);
            logger.info("简单邮件已经发送。");
        }catch (Exception e){
            logger.error("发送简单邮件时发生异常！", e);
        }

    }

    @Override
    public boolean verifyEmail(String send, String receive) {
        return send.equals(receive);
    }

    @Override
    public String generateVerificationCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < emailCodeLength; i++) {
            code.append(random.nextInt(10));
        }
        logger.info("验证码是："+code.toString());
        return code.toString();
    }
}
