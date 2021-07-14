package com.rookiestar.starmanager.controller;

import com.rookiestar.starmanager.entity.assessment.Assessment;
import com.rookiestar.starmanager.entity.employee.Employee;
import com.rookiestar.starmanager.entity.experience.Experience;
import com.rookiestar.starmanager.constant.AttributeNames;
import com.rookiestar.starmanager.constant.PermissionNames;
import com.rookiestar.starmanager.constant.RoleNames;
import com.rookiestar.starmanager.service.CreateService;
import com.rookiestar.starmanager.service.EmailService;
import com.rookiestar.starmanager.service.RetrieveService;
import com.rookiestar.starmanager.service.UpdateService;
import com.rookiestar.starmanager.util.DateUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
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
    @RequiresRoles(value = {RoleNames.COMPANY_MANAGER,RoleNames.MANAGER},logical = Logical.OR)
    @RequiresPermissions(value = {PermissionNames.READ})
    @RequestMapping("/getAllEmployees.do")
    public List<Employee> getAllEmployees(){
        Session session = SecurityUtils.getSubject().getSession(false);
        int companyId = (int)session.getAttribute(AttributeNames.COMPANY_ID);
        return retrieveService.retrieveAllEmployeesByCompany(companyId);
    }

    /**
     * 请求描述：通过企业id获取该企业所有在职员工
     * 请求地址：  /getPresentEmployees.do
     * 请求参数：
     * 返回值：List<Employee>
     */
    @RequiresRoles(value = {RoleNames.COMPANY_MANAGER,RoleNames.MANAGER},logical = Logical.OR)
    @RequiresPermissions(value = {PermissionNames.READ})
    @RequestMapping("/getPresentEmployees.do")
    public List<Employee> getPresentEmployees(){
        Session session = SecurityUtils.getSubject().getSession(false);
        int companyId = (int)session.getAttribute(AttributeNames.COMPANY_ID);
        return retrieveService.retrievePresentEmployeesByCompany(companyId);
    }

    /**
     * 请求描述：录用员工，向数据库添加员工在职经历，生成工号和录用日期
     * 请求地址：  /hireEmployee.do
     * 请求参数：int accountNumber 员工账号，int departmentId 部门号，int positionId 职位号
     * 返回值：Experience 员工在职经历
     */
    @RequiresRoles(value = {RoleNames.COMPANY_MANAGER,RoleNames.MANAGER},logical = Logical.OR)
    @RequiresPermissions(value = {PermissionNames.WRITE})
    @RequestMapping("/hireEmployee.do")
    public Experience hireEmployee(Experience experience) throws Exception{
        Session session = SecurityUtils.getSubject().getSession(false);
        int companyId = (int)session.getAttribute(AttributeNames.COMPANY_ID);
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
    @RequiresRoles(value = {RoleNames.COMPANY_MANAGER,RoleNames.MANAGER},logical = Logical.OR)
    @RequiresPermissions(value = {PermissionNames.WRITE})
    @RequestMapping("/registerEmployee.do")
    public Employee registerEmployee(String name, String birthday, String gender, String email, String identifyNumber, String password) throws Exception{
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
    @RequiresRoles(value = {RoleNames.COMPANY_MANAGER,RoleNames.MANAGER},logical = Logical.OR)
    @RequiresPermissions(value = {PermissionNames.READ})
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
    @RequiresRoles(value = {RoleNames.COMPANY_MANAGER,RoleNames.MANAGER},logical = Logical.OR)
    @RequiresPermissions(value = {PermissionNames.READ})
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
    @RequiresRoles(value = {RoleNames.COMPANY_MANAGER,RoleNames.MANAGER},logical = Logical.OR)
    @RequiresPermissions(value = {PermissionNames.READ})
    @RequestMapping("/getEmployeesByGender.do")
    public List<Employee> getEmployeesByGender(String gender){
        return retrieveService.retrieveEmployeesByGender(gender);
    }

    /**
     * 请求描述：通过邮箱查询员工信息
     * 请求地址：  /getEmployeeByEmail.do
     * 请求参数：String email 邮箱
     * 返回值：Employee 员工
     */
    @RequiresRoles(value = {RoleNames.COMPANY_MANAGER,RoleNames.MANAGER},logical = Logical.OR)
    @RequiresPermissions(value = {PermissionNames.READ})
    @RequestMapping(value = "getEmployeeByEmail.do")
    public Employee getEmployeeByEmail(String email){
        return retrieveService.retrieveEmployeeByEmail(email);
    }


    /**
     * 请求描述：更新员工评价
     * 请求地址：  /updateAssessment.do
     * 请求参数：int accountNumber 员工账号,int companyId 企业Id, String startTime 就职时间, String absenteeismRate 缺勤率, String performance 绩效
     * 返回值：boolean 更新是否成功
     */
    @RequiresRoles(value = {RoleNames.COMPANY_MANAGER,RoleNames.MANAGER},logical = Logical.OR)
    @RequiresPermissions(value = {PermissionNames.WRITE})
    @RequestMapping(value = "/updateAssessment.do")
    public boolean updateAssessment(int accountNumber, int companyId, String startTime, String absenteeismRate, String performance) throws Exception {
        Assessment assessment = new Assessment(accountNumber, companyId, DateUtil.parse(startTime), absenteeismRate, performance);
        return updateService.updateAssessment(assessment);
    }

    /**
     * 请求描述：更新员工就职经历
     * 请求地址：  /updateExperience.do
     * 请求参数：int accountNumber 员工账号,int companyId 企业Id,int departmentId 部门编号,int positionId 职位号,int jobNumber 工号,String startTime 就职时间,String endTime 离职时间,boolean isEnd 是否离职
     * 返回值：boolean 更新是否成功
     */
    @RequiresRoles(value = {RoleNames.COMPANY_MANAGER,RoleNames.MANAGER},logical = Logical.OR)
    @RequiresPermissions(value = {PermissionNames.WRITE})
    @RequestMapping(value = "/updateExperience.do")
    public boolean updateExperience(int accountNumber, int companyId,
                                    int departmentId, int positionId, int jobNumber,
                                    String startTime, String endTime, boolean isEnd) throws Exception {
        Experience experience = new Experience(accountNumber, companyId, departmentId, positionId, jobNumber,
                DateUtil.parse(startTime), DateUtil.parse(endTime), isEnd);
        return updateService.updateExperience(experience);
    }
}
