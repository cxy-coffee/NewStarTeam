package com.rookiestar.starmanager.controller;

import com.rookiestar.starmanager.constant.UserTypes;
import com.rookiestar.starmanager.entity.companymanager.CompanyManager;
import com.rookiestar.starmanager.constant.AttributeNames;
import com.rookiestar.starmanager.exception.CheckVerificationCodeException;
import com.rookiestar.starmanager.service.EmailService;
import com.rookiestar.starmanager.shiro.token.GenericToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class that handle the request of login
 *
 * @author 曹向阳
 * @date 2021/7/12
 */
@RestController
public class LoginRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmailService emailService;

    @RequestMapping("/")
    public String defaultPage() {
        return "首页";
    }

    /**
     * 请求描述：向某个邮箱发送6位验证码
     * 请求地址：  /sendEmailCode.do
     * 请求参数：String to 邮箱地址
     * 返回值：void
     */
    @RequestMapping("/sendEmailCode.do")
    public void sendEmailCode(String to){
        String subject = "邮箱验证码";
        String code = emailService.generateVerificationCode();
        String content = "邮箱验证码为："+code;
        emailService.sendSimpleEmail(to,subject,content);

        Subject userSubject = SecurityUtils.getSubject();
        userSubject.logout();
        Session session = userSubject .getSession(true);
        session.setAttribute(AttributeNames.VERIFICATION_CODE,code);
        session.setAttribute(AttributeNames.EMAIL_TO,to);
    }

    /**
     * 请求描述：做验证码检验
     * 请求地址：  /checkVerificationCode.do
     * 请求参数：String verificationCode 验证码
     * 返回值：String 如果成功，返回："验证通过"。否则返回错误信息。
     */
    @RequestMapping("/checkVerificationCode.do")
    public String checkVerificationCode(String verificationCode){
        Subject userSubject = SecurityUtils.getSubject();
        Session session = userSubject .getSession(false);
        if(session==null){
            throw new CheckVerificationCodeException("请通过验证码验证");
        }
        session.setAttribute(AttributeNames.VERIFY_RESULT,false);
        if(verificationCode==null){
            throw new CheckVerificationCodeException("请输入验证码");
        }
        if(!verificationCode.equals(session.getAttribute(AttributeNames.VERIFICATION_CODE))){
            throw new CheckVerificationCodeException("验证码错误");
        }
        session.setAttribute(AttributeNames.VERIFY_RESULT,true);
        return "验证通过";
    }

    /**
     * 请求描述：员工登录
     * 请求地址：  /employeeLogin.do
     * 请求参数：Integer accountNumber 员工账号, String password 密码
     * 返回值：String 如果成功，返回："登录成功"。否则返回错误信息。
     */
    @RequestMapping("/employeeLogin.do")
    public String employeeLogin(Integer accountNumber, String password){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        Session session = subject.getSession(true);
        if (!subject.isAuthenticated()) {
            GenericToken token = new GenericToken(accountNumber.toString(), password);
            token.setUserType(UserTypes.EMPLOYEE);
            subject.login(token);
            session.setAttribute(AttributeNames.ACCOUNT_NUMBER,accountNumber);
        }
        logger.info("登录成功。欢迎您："+session.getAttribute(AttributeNames.ACCOUNT_NUMBER));
        return "登录成功";
    }

    /**
     * 请求描述：企业管理者登录
     * 请求地址：  /companyLogin.do
     * 请求参数：Integer companyId 企业Id,Integer jobNumber 工号,String password 密码
     * 返回值：String 如果成功，返回："登录成功"。否则返回错误信息。
     */
    @RequestMapping("/companyLogin.do")
    public String companyLogin(Integer companyId,Integer jobNumber,String password){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(false);
        if (session == null||session.getAttribute(AttributeNames.VERIFY_RESULT)==null||!(boolean)session.getAttribute(AttributeNames.VERIFY_RESULT)) {
            throw new CheckVerificationCodeException("请通过验证码验证");
        }
        if (!subject.isAuthenticated()) {
            GenericToken token = new GenericToken(jobNumber.toString(), password);
            token.setUserType(UserTypes.COMPANY_MANAGER);
            token.setCompanyManager(new CompanyManager(companyId, session.getAttribute(AttributeNames.EMAIL_TO).toString(), jobNumber, password));
            subject.login(token);
            session.setAttribute(AttributeNames.COMPANY_ID,companyId);
            session.setAttribute(AttributeNames.JOB_NUMBER,jobNumber);
        }
        logger.info("登录成功。欢迎您："+session.getAttribute(AttributeNames.COMPANY_ID)+"企业的主管"+session.getAttribute(AttributeNames.JOB_NUMBER));
        return "登录成功";
    }
}
