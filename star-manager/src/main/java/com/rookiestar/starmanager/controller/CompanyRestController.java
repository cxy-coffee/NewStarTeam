package com.rookiestar.starmanager.controller;

import com.rookiestar.starmanager.entity.assessment.Assessment;
import com.rookiestar.starmanager.entity.department.Department;
import com.rookiestar.starmanager.entity.employee.Employee;
import com.rookiestar.starmanager.entity.experience.Experience;
import com.rookiestar.starmanager.constant.AttributeNames;
import com.rookiestar.starmanager.constant.PermissionNames;
import com.rookiestar.starmanager.constant.RoleNames;
import com.rookiestar.starmanager.entity.position.Position;
import com.rookiestar.starmanager.exception.HireException;
import com.rookiestar.starmanager.exception.RequestParameterException;
import com.rookiestar.starmanager.rabbit.MessageProducer;
import com.rookiestar.starmanager.service.*;
import com.rookiestar.starmanager.util.DateUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private CreateService createService;
    @Autowired
    private DeleteService deleteService;
    @Autowired
    private MessageProducer messageProducer;

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
    public Experience hireEmployee(Integer accountNumber,Integer departmentId,Integer positionId) throws Exception{
        if(accountNumber==null||departmentId==null||positionId==null){
            throw new RequestParameterException("请求参数不正确");
        }

        Employee employee = retrieveService.retrieveEmployeeByAccountNumber(accountNumber);
        if(employee==null){
            throw new HireException("员工尚未注册");
        }

        Session session = SecurityUtils.getSubject().getSession(false);
        int companyId = (int)session.getAttribute(AttributeNames.COMPANY_ID);

        Experience experience = new Experience();
        experience.setAccountNumber(accountNumber);
        experience.setDepartmentId(departmentId);
        experience.setPositionId(positionId);
        experience.setCompanyId(companyId);

        Experience newExperience = createService.hireEmployee(experience);

        String content = "您好，"+employee.getName()+"，您已被录用，您的工号为："+newExperience.getJobNumber();
        Map<String,String> contentMap = new HashMap<>(10);
        contentMap.put("to",employee.getEmail());
        contentMap.put("subject","录用通知");
        contentMap.put("content",content);
        messageProducer.sendNotice(contentMap);

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
        if(name==null||birthday==null||gender==null||email==null||identifyNumber==null||password==null){
            throw new RequestParameterException("请求参数不正确");
        }

        Employee employee = new Employee(name, DateUtil.parse(birthday),gender,email,identifyNumber,0,password,null);
        Employee newEmployee = createService.registerEmployee(employee);

        String content = "您好，"+newEmployee.getName()+"，您已成功注册，您的账号为："+newEmployee.getAccountNumber();
        Map<String,String> contentMap = new HashMap<>(10);
        contentMap.put("to",employee.getEmail());
        contentMap.put("subject","注册通知");
        contentMap.put("content",content);
        messageProducer.sendNotice(contentMap);

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
        if(name==null){
            throw new RequestParameterException("请求参数不正确");
        }
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
        if(identifyNumber==null){
            throw new RequestParameterException("请求参数不正确");
        }
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
        if(gender==null){
            throw new RequestParameterException("请求参数不正确");
        }
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
    @RequestMapping(value = "/getEmployeeByEmail.do")
    public Employee getEmployeeByEmail(String email){
        if(email==null){
            throw new RequestParameterException("请求参数不正确");
        }
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
    public boolean updateAssessment(Integer accountNumber, Integer companyId, String startTime, String absenteeismRate, String performance) throws Exception {
        if(accountNumber==null||companyId==null||startTime==null||absenteeismRate==null||performance==null){
            throw new RequestParameterException("请求参数不正确");
        }
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
    public boolean updateExperience(Integer accountNumber, Integer companyId,
                                    Integer departmentId, Integer positionId, Integer jobNumber,
                                    String startTime, String endTime, Boolean isEnd) throws Exception {
        if(accountNumber==null||companyId==null||departmentId==null||positionId==null||jobNumber==null||startTime==null||endTime==null||isEnd==null){
            throw new RequestParameterException("请求参数不正确");
        }
        Experience experience = new Experience(accountNumber, companyId, departmentId, positionId, jobNumber,
                DateUtil.parse(startTime), DateUtil.parse(endTime), isEnd);
        return updateService.updateExperience(experience);
    }

    /**
     * 请求描述：更新企业部门
     * 请求地址：  /updateDepartment.do
     * 请求参数：int companyId 企业Id,int departmentId 部门编号,String name 部门名
     * 返回值：boolean 是否更新成功
     */
    @RequiresRoles(value = {RoleNames.COMPANY_MANAGER,RoleNames.MANAGER},logical = Logical.OR)
    @RequiresPermissions(value = {PermissionNames.WRITE})
    @RequestMapping(value = "/updateDepartment.do")
    public boolean updateDepartment(Integer companyId,Integer departmentId,String name){
        if(companyId==null||departmentId==null||name==null){
            throw new RequestParameterException("请求参数不正确");
        }
        Department department = new Department(companyId, departmentId, name, null);
        return updateService.updateDepartment(department);
    }

    /**
     * 请求描述：更新部门的职位
     * 请求地址：  /updatePosition.do
     * 请求参数：int companyId 企业Id,int departmentId 部门编号,int positionId 职位编号,String name 职位名
     * 返回值：boolean 是否更新成功
     */
    @RequiresRoles(value = {RoleNames.COMPANY_MANAGER,RoleNames.MANAGER},logical = Logical.OR)
    @RequiresPermissions(value = {PermissionNames.WRITE})
    @RequestMapping(value = "/updatePosition.do")
    public boolean updatePosition(Integer companyId,Integer departmentId,Integer positionId,String name){
        if(companyId==null||departmentId==null||positionId==null||name==null){
            throw new RequestParameterException("请求参数不正确");
        }
        Position position = new Position(companyId, departmentId, positionId, name);
        return updateService.updatePosition(position);
    }

    /**
     * 请求描述：删除职位
     * 请求地址：  /deletePosition.do
     * 请求参数：int companyId 公司Id,int departmentId 部门编号,int positionId 职位编号
     * 返回值：boolean 是否删除成功
     */
    @RequiresRoles(value = {RoleNames.COMPANY_MANAGER,RoleNames.MANAGER},logical = Logical.OR)
    @RequiresPermissions(value = {PermissionNames.WRITE})
    @RequestMapping(value = "/deletePosition.do")
    public boolean deletePosition(Integer companyId,Integer departmentId,Integer positionId){
        if(companyId==null||departmentId==null||positionId==null){
            throw new RequestParameterException("请求参数不正确");
        }
        return deleteService.deletePositionByCompanyIdAndDepartmentIdAndPositionId(companyId,departmentId,positionId);
    }

    /**
     * 请求描述：删除部门
     * 请求地址：  /deleteDepartment.do
     * 请求参数：int companyId 企业Id,int departmentId 部门编号
     * 返回值：boolean 是否删除成功
     */
    @RequiresRoles(value = {RoleNames.COMPANY_MANAGER,RoleNames.MANAGER},logical = Logical.OR)
    @RequiresPermissions(value = {PermissionNames.WRITE})
    @RequestMapping(value = "/deleteDepartment.do")
    public boolean deleteDepartment(Integer companyId,Integer departmentId){
        if(companyId==null||departmentId==null){
            throw new RequestParameterException("请求参数不正确");
        }
        return deleteService.deleteDepartmentByCompanyIdAndDepartmentId(companyId,departmentId);
    }

    /**
     * 请求描述：通过公司ID获得部门信息
     * 请求地址：    /getDepartmentByCompanyId.do
     * 请求参数：int companyId 公司Id
     * 返回值：List<Department> 部门列表
     */
    @RequiresRoles(value = {RoleNames.COMPANY_MANAGER,RoleNames.MANAGER},logical = Logical.OR)
    @RequiresPermissions(value = {PermissionNames.READ})
    @RequestMapping(value = "/getDepartmentByCompanyId.do")
    public List<Department> getDepartmentByCompanyId(Integer companyId){
        if(companyId==null){
            throw new RequestParameterException("请求参数不正确");
        }
        return retrieveService.retrieveDepartmentByCompanyId(companyId);
    }

    /**
     * 请求描述 ：通过公司Id和部门Id获得职位信息
     * 请求地址：    /getPositionByCompanyIdAndDepartmentId.do
     * 请求参数：int companyId 公司Id， int departmentId 部门Id
     * 返回值：List<Position> 职位列表
     */
    @RequiresRoles(value = {RoleNames.COMPANY_MANAGER,RoleNames.MANAGER},logical = Logical.OR)
    @RequiresPermissions(value = {PermissionNames.READ})
    @RequestMapping(value = "/getPositionByCompanyIdAndDepartmentId.do")
    public List<Position> getPositionByCompanyIdAndDepartmentId(Integer companyId,Integer departmentId){
        if(companyId==null||departmentId==null){
            throw new RequestParameterException("请求参数不正确");
        }
        return retrieveService.retrievePositionByCompanyIdAndDepartmentId(companyId,departmentId);
    }


    /**
     * 请求描述：通过公司Id和部门Id查询某部门所有员工信息
     * 请求地址：    /getEmployeeByCompanyIdAndDepartmentId.do
     * 请求参数：int companyId 公司Id， int departmentId 部门Id
     * 返回值：List<Employee> 员工列表
     */
    @RequiresRoles(value = {RoleNames.COMPANY_MANAGER,RoleNames.MANAGER},logical = Logical.OR)
    @RequiresPermissions(value = {PermissionNames.READ})
    @RequestMapping(value = "/getEmployeeByCompanyIdAndDepartmentId.do")
    public List<Employee> getEmployeeByCompanyIdAndDepartmentId(Integer companyId,Integer departmentId){
        if(companyId==null||departmentId==null){
            throw new RequestParameterException("请求参数不正确");
        }
        return retrieveService.retrieveEmployeesByCompanyIdAndDepartmentId(companyId,departmentId);
    }

    /**
     * 请求描述：通过公司Id，部门Id和职位Id查询本职位所有员工信息
     * 请求地址：    /getEmployeeByCompanyIdAndDepartmentIdAndPositionId.do
     * 请求参数：int companyId 公司Id， int departmentId 部门Id， int positionId 职位Id
     * 返回值：List<Employee>员工列表
     */
    @RequiresRoles(value = {RoleNames.COMPANY_MANAGER,RoleNames.MANAGER},logical = Logical.OR)
    @RequiresPermissions(value = {PermissionNames.READ})
    @RequestMapping(value = "/getEmployeeByCompanyIdAndDepartmentIdAndPositionId.do")
    public List<Employee> getEmployeeByCompanyIdAndDepartmentIdAndPositionId(Integer companyId,Integer departmentId,Integer positionId){
        if(companyId==null||departmentId==null||positionId==null){
            throw new RequestParameterException("请求参数不正确");
        }
        return retrieveService.retrieveEmployeesByCompanyIdAndDepartmentIdAndPositionId(companyId,departmentId,positionId);
    }


}
