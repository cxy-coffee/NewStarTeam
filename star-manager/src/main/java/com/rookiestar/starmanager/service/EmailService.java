package com.rookiestar.starmanager.service;

/**
 * Service class that handle email service
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
public interface EmailService {
    /**
     * send an email with subject and content to a destination
     *
     * @param to the destination of email
     * @param subject the subject of email
     * @param content the content of email
     */
    void sendSimpleEmail(String to,String subject,String content);

    /**
     * compare the content that is sent to the content that someone receives
     *
     * @param send the content that is sent
     * @param receive the content that someone receives
     * @return boolean
     */
    boolean verifyEmail(String send,String receive);

    /**
     * generate verification code
     *
     * @return String
     */
    String generateVerificationCode();
}
