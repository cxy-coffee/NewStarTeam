package com.rookiestar.starmanager.controller;

import com.rookiestar.starmanager.constant.UserTypes;
import com.rookiestar.starmanager.entity.company.CompanyToReview;
import com.rookiestar.starmanager.entity.companymanager.CompanyManager;
import com.rookiestar.starmanager.constant.AttributeNames;
import com.rookiestar.starmanager.exception.CheckVerificationCodeException;
import com.rookiestar.starmanager.exception.RequestParameterException;
import com.rookiestar.starmanager.rabbit.MessageProducer;
import com.rookiestar.starmanager.service.CreateService;
import com.rookiestar.starmanager.service.EmailService;
import com.rookiestar.starmanager.shiro.token.GenericToken;
import org.apache.catalina.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
    @Autowired
    private MessageProducer messageProducer;
    @Autowired
    private CreateService createService;

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
        if(to==null){
            throw new RequestParameterException("请求参数不正确");
        }
        String subject = "邮箱验证码";
        String code = emailService.generateVerificationCode();
        String content = "邮箱验证码为："+code;

        Map<String,String> contentMap = new HashMap<>(10);
        contentMap.put("to",to);
        contentMap.put("subject",subject);
        contentMap.put("content",content);

        messageProducer.sendEmailCode(contentMap);

        Subject userSubject = SecurityUtils.getSubject();
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
        if(verificationCode==null){
            throw new RequestParameterException("请求参数不正确");
        }
        Subject userSubject = SecurityUtils.getSubject();
        Session session = userSubject .getSession(false);
        if(session==null){
            throw new CheckVerificationCodeException("请通过验证码验证");
        }
        session.setAttribute(AttributeNames.VERIFY_RESULT,false);
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
        if(accountNumber==null||password==null){
            throw new RequestParameterException("请求参数不正确");
        }
        Subject subject = SecurityUtils.getSubject();
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
        if(companyId==null||jobNumber==null||password==null){
            throw new RequestParameterException("请求参数不正确");
        }
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

    /**
     * 请求描述：超管登录
     * 请求地址：  /managerLogin.do
     * 请求参数：Integer accountNumber 超管账号, String password 超管密码
     * 返回值：String 如果成功，返回："登录成功"。否则返回错误信息。
     */
    @RequestMapping("/managerLogin.do")
    public String managerLogin(Integer accountNumber, String password){
        if(accountNumber==null||password==null){
            throw new RequestParameterException("请求参数不正确");
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(true);
        if (!subject.isAuthenticated()) {
            GenericToken token = new GenericToken(accountNumber.toString(), password);
            token.setUserType(UserTypes.MANAGER);
            subject.login(token);
            session.setAttribute(AttributeNames.MANAGER_ACCOUNT_NUMBER,accountNumber);
        }
        logger.info("登录成功。欢迎您："+session.getAttribute(AttributeNames.MANAGER_ACCOUNT_NUMBER));
        return "登录成功";
    }

    /**
     * 请求描述：检查登录状态
     * 请求地址：  /checkLoginState.do
     * 请求参数：String userType 用户类型，固定值：employee，companyManager，manager
     * 返回值：boolean 若已登录，则返回true，否则返回false
     */
    @RequestMapping("/checkLoginState.do")
    public boolean checkLoginState(String userType){
        if(userType==null){
            throw new RequestParameterException("请求参数不正确");
        }
        Subject subject = SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();
        if(principal==null){
            return false;
        }
        GenericToken token = (GenericToken)principal;
        return token.getUserType().equals(userType);
    }
    /**
     * 请求描述：登出
     * 请求地址：  /logout.do
     * 请求参数：
     * 返回值：String 若已登录，则返回"登出成功"，否则返回"未登录"
     */
    @RequestMapping("/logout.do")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            return "未登录";
        }else {
            subject.logout();
            return "登出成功";
        }
    }

    /**
     * 请求描述：注册企业
     * 请求地址：  /registerCompany.do
     * 请求参数：String name 企业名, String legalRepresentativeName 法人代表名, String email 企业邮箱, String address 企业地址, String phone 企业电话
     * 返回值：CompanyToReview 待确认企业
     */
    @RequestMapping(value = "/registerCompany.do")
    public CompanyToReview registerCompany(String name, String legalRepresentativeName, String email, String address, String phone){
        if(name==null||legalRepresentativeName==null||email==null||address==null||phone==null){
            throw new RequestParameterException("请求参数不正确");
        }
        CompanyToReview companyToReview=new CompanyToReview(name,legalRepresentativeName,email,address,phone);
        CompanyToReview newCompanyToReview=createService.addCompanyToReview(companyToReview);
        String content = "您的公司注册申请已提交，正在等待管理员审核\n"+
                "您的公司信息为：\n公司名称："+companyToReview.getName()+"\n"
                +"公司法人代表："+companyToReview.getLegalRepresentativeName()+"\n"
                +"公司邮箱："+companyToReview.getEmail()+"\n"
                +"公司地址："+companyToReview.getAddress()+"\n"
                +"公司电话："+companyToReview.getPhone()+"\n"
                +"请您确认。";
        emailService.sendSimpleEmail("2019302110260@whu.edu.cn","注册通知",content);
        return newCompanyToReview;
    }
}
