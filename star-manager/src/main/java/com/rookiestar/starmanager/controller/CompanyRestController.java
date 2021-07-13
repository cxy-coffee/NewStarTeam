package com.rookiestar.starmanager.controller;

import com.rookiestar.starmanager.entity.assessment.Assessment;
import com.rookiestar.starmanager.entity.employee.Employee;
import com.rookiestar.starmanager.entity.experience.Experience;
import com.rookiestar.starmanager.service.CreateService;
import com.rookiestar.starmanager.service.EmailService;
import com.rookiestar.starmanager.service.RetrieveService;
import com.rookiestar.starmanager.service.UpdateService;
import com.rookiestar.starmanager.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class that handle the request of company
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
@RestController
public class CompanyRestController {
    @Autowired
    private UpdateService updateService;
    @Autowired
    private RetrieveService retrieveService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private CreateService createService;

    /**
     * 请求描述：通过企业id获取该企业所有员工（包括已离职员工）
     * 请求地址：  /getAllEmployees.do
     * 请求参数：
     * 返回值：List<Employee>
     */
    @RequestMapping("/getAllEmployees.do")
    public List<Employee> getAllEmployees(){
        int companyId = 1;
        return retrieveService.retrieveAllEmployeesByCompany(companyId);
    }

    /**
     * 请求描述：通过企业id获取该企业所有在职员工
     * 请求地址：  /getPresentEmployees.do
     * 请求参数：
     * 返回值：List<Employee>
     */
    @RequestMapping("/getPresentEmployees.do")
    public List<Employee> getPresentEmployees(){
        int companyId = 1;
        return retrieveService.retrievePresentEmployeesByCompany(companyId);
    }

    /**
     * 请求描述：录用员工，向数据库添加员工在职经历，生成工号和录用日期
     * 请求地址：  /hireEmployee.do
     * 请求参数：int accountNumber 员工账号，int departmentId 部门号，int positionId 职位号
     * 返回值：Experience 员工在职经历
     */
    @RequestMapping("/hireEmployee.do")
    public Experience hireEmployee(Experience experience) throws Exception{
        int companyId = 1;
        experience.setCompanyId(companyId);
        Experience newExperience = createService.hireEmployee(experience);
        String content = "您已被录用，您的工号为："+newExperience.getJobNumber();
        emailService.sendSimpleEmail("2019302110260@whu.edu.cn","录用通知",content);
        return newExperience;
    }

    /**
     * 请求描述：注册员工，生成员工账号
     * 请求地址：  /registerEmployee.do
     * 请求参数：String name 姓名, String birthday 出生年月日（yyyy-MM-dd）,String gender 性别,String email 邮箱地址,String identifyNumber 身份证号,String password 密码
     * 返回值：Employee 员工对象
     */
    @RequestMapping("/registerEmployee.do")
    public Employee registerEmployee(String name, String birthday,String gender,String email,String identifyNumber,String password) throws Exception{
        Employee employee = new Employee(name, DateUtil.parse(birthday),gender,email,identifyNumber,0,password,null);
        Employee newEmployee = createService.registerEmployee(employee);
        String content = "您已成功注册，您的账号为："+newEmployee.getAccountNumber();
        emailService.sendSimpleEmail("2019302110260@whu.edu.cn","注册通知",content);
        return newEmployee;
    }

    /**
     * 请求描述：通过员工姓名查询员工
     * 请求地址：  /getEmployeesByName.do
     * 请求参数：String name 员工姓名
     * 返回值：List<Employee> 员工列表
     */
    @RequestMapping("/getEmployeesByName.do")
    public List<Employee> getEmployeesByName(String name){
        return retrieveService.retrieveEmployeesByName(name);
    }

    /**
     * 请求描述：通过员工身份证号查询员工
     * 请求地址：  /getEmployeeByIdentifyNumber.do
     * 请求参数：String identifyNumber 员工身份证号
     * 返回值：Employee 员工
     */
    @RequestMapping("/getEmployeeByIdentifyNumber.do")
    public Employee getEmployeeByIdentifyNumber(String identifyNumber){
        return retrieveService.retrieveEmployeeByIdentifyNumber(identifyNumber);
    }

    /**
     * 请求描述：通过性别查询员工信息
     * 请求地址：  /getEmployeesByGender.do
     * 请求参数：String gender 性别
     * 返回值：List<Employee> 员工列表
     */
    @RequestMapping("/getEmployeesByGender.do")
    public List<Employee> getEmployeesByGender(String gender){
        return retrieveService.retrieveEmployeesByGender(gender);
    }

    @RequestMapping(value = "getEmployeeByEmail.do")
    public Employee getEmployeeByEmail(String email){
        return retrieveService.retrieveEmployeeByEmail(email);
    }

    @RequestMapping(value = "/updateEmployee.do")
    public boolean updateEmployee(String name, String birthday,String gender,String email,String identifyNumber,String password) throws Exception {
        Employee employee = new Employee(name, DateUtil.parse(birthday),gender,email,identifyNumber,0,password,null);
        return updateService.updateEmployee(employee);
    }

    @RequestMapping(value = "/updateAssessment.do")
    public boolean updateAssessment(int accountNumber,int companyId, String startTime, String absenteeismRate, String performance) throws Exception {
        Assessment assessment = new Assessment(accountNumber, companyId, DateUtil.parse(startTime), absenteeismRate, performance);
        return updateService.updateAssessment(assessment);
    }


    @RequestMapping(value = "/updateExperience.do")
    public boolean updateExperience(int accountNumber,int companyId,
                                    int departmentId,int positionId,int jobNumber,
                                    String startTime,String endTime,boolean isEnd) throws Exception {
        Experience experience = new Experience(accountNumber, companyId, departmentId, positionId, jobNumber,
                DateUtil.parse(startTime), DateUtil.parse(endTime), isEnd);
        return updateService.updateExperience(experience);
    }
}
