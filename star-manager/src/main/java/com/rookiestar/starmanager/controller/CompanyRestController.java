package com.rookiestar.starmanager.controller;

import com.rookiestar.starmanager.entity.Employee;
import com.rookiestar.starmanager.entity.Experience;
import com.rookiestar.starmanager.service.CreateService;
import com.rookiestar.starmanager.service.EmailService;
import com.rookiestar.starmanager.service.RetrieveService;
import com.rookiestar.starmanager.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Controller class that handle the request of company
 *
 * @author 86199
 * @date 2021/7/9
 */
@RestController
public class CompanyRestController {

    @Autowired
    private RetrieveService retrieveService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private CreateService createService;

    @RequestMapping("/getAllEmployees.do")
    public List<Employee> getAllEmployees(){
        int companyId = 1;
        return retrieveService.retrieveAllEmployeesByCompany(companyId);
    }

    @RequestMapping("/getPresentEmployees.do")
    public List<Employee> getPresentEmployees(){
        int companyId = 1;
        return retrieveService.retrievePresentEmployeesByCompany(companyId);
    }

    @RequestMapping("/sendEmailCode.do")
    public String sendEmailCode(String to){
        String subject = "邮箱验证码";
        String code = emailService.generateVerificationCode();
        String content = "邮箱验证码为："+code;
        emailService.sendSimpleEmail(to,subject,content);
        return "{\"verificationCode\":"+code+"}";
    }

    @RequestMapping("/hireEmployee.do")
    public Experience hireEmployee(Experience experience) throws Exception{
        int companyId = 1;
        experience.setCompanyId(companyId);
        Experience newExperience = createService.hireEmployee(experience);
        String content = "您已被录用，您的工号为："+newExperience.getJobNumber();
        emailService.sendSimpleEmail("2019302110260@whu.edu.cn","录用通知",content);
        return newExperience;
    }

    @RequestMapping("/registerEmployee.do")
    public Employee registerEmployee(String name, String birthday,String gender,String email,String identifyNumber,String password) throws Exception{
        Employee employee = new Employee(name, DateUtil.parse(birthday),gender,email,identifyNumber,0,password,null);
        Employee newEmployee = createService.registerEmployee(employee);
        String content = "您已成功注册，您的账号为："+newEmployee.getAccountNumber();
        emailService.sendSimpleEmail("2019302110260@whu.edu.cn","注册通知",content);
        return newEmployee;
    }

    @RequestMapping("/testTime.do")
    public Date testTime(){
        return new Date();
    }
}
