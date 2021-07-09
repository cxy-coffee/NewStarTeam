package com.rookiestar.starmanager.controller;

import com.rookiestar.starmanager.entity.Employee;
import com.rookiestar.starmanager.entity.Experience;
import com.rookiestar.starmanager.service.CreateService;
import com.rookiestar.starmanager.service.EmailService;
import com.rookiestar.starmanager.service.RetrieveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public Experience hireEmployee(Experience experience){
        int companyId = 1;
        experience.setCompanyId(companyId);
        return createService.hireEmployee(experience);
    }

    @RequestMapping("/registerEmployee")
    public Employee registerEmployee(Employee employee){
        return createService.registerEmployee(employee);
    }
}
