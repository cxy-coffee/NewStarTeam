package com.rookiestar.starmanager.service;

public interface EmailService {
    void sendSimpleEmail(String to,String subject,String content);
    boolean verifyEmail(String send,String receive);
    String generateVerificationCode();
}
