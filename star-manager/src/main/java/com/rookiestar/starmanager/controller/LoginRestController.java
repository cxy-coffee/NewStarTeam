package com.rookiestar.starmanager.controller;

import com.rookiestar.starmanager.entity.companyManager.CompanyManager;
import com.rookiestar.starmanager.myException.CheckVerificationCodeException;
import com.rookiestar.starmanager.service.EmailService;
import com.rookiestar.starmanager.shiro.token.CompanyToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${userType.employeePre}")
    private String employeePre;
    @Value("${userType.companyPre}")
    private String companyPre;
    @Value("${userType.managerPre}")
    private String managerPre;

    @Autowired
    private EmailService emailService;

    @RequestMapping("/")
    public String defaultPage() {
        return "首页";
    }

    /**
     * 请求描述：向某个邮箱发送${emailService.emailCodeLength}位验证码
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
        session.setAttribute("verificationCode",code);
        session.setAttribute("to",to);
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
        session.setAttribute("verifyResult",false);
        if(verificationCode==null){
            throw new CheckVerificationCodeException("请输入验证码");
        }
        if(!verificationCode.equals(session.getAttribute("verificationCode"))){
            throw new CheckVerificationCodeException("验证码错误");
        }
        session.setAttribute("verifyResult",true);
        return "验证通过";
    }

    @RequestMapping("/employeeLogin.do")
    public String employeeLogin(String username, String password) throws Exception{
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        Session session = subject.getSession(true);
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(employeePre + username, password);

            subject.login(token);
            session.setAttribute("accountNumber",username);
        }
        return "登录成功。欢迎您："+session.getAttribute("accountNumber");
    }

    @RequestMapping("/companyLogin.do")
    public String companyLogin(Integer companyId,Integer jobNumber,String password) throws Exception{
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(false);
        if (session == null||session.getAttribute("verifyResult")==null||!(boolean)session.getAttribute("verifyResult")) {
            throw new CheckVerificationCodeException("请通过验证码验证");
        }
        if (!subject.isAuthenticated()) {
            CompanyToken token = new CompanyToken(companyPre + jobNumber, password);
            token.setCompanyManager(new CompanyManager(companyId, session.getAttribute("to").toString(), jobNumber, password));
            subject.login(token);
            session.setAttribute("companyId",companyId);
            session.setAttribute("jobNumber",jobNumber);
        }
        return "登录成功。欢迎您："+session.getAttribute("companyId")+"企业的主管"+session.getAttribute("jobNumber");
    }
}
