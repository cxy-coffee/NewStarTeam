package com.rookiestar.starmanager.controller;

import com.rookiestar.starmanager.constant.AttributeNames;
import com.rookiestar.starmanager.constant.PermissionNames;
import com.rookiestar.starmanager.constant.RoleNames;
import com.rookiestar.starmanager.entity.employee.Employee;
import com.rookiestar.starmanager.exception.RequestParameterException;
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


/**
 * Controller class that handle the request of employee
 *
 * @author 曹向阳
 * @date 2021/7/13
 */
@RestController
public class EmployeeRestController {
    @Autowired
    private UpdateService updateService;
    @Autowired
    private RetrieveService retrieveService;
    /**
     * 请求描述：更新员工基本信息
     * 请求地址：  /updateEmployee.do
     * 请求参数：String birthday 出生年月日（yyyy-MM-dd）,String email 邮箱,String identifyNumber 身份证号
     * 返回值：boolean 更新是否成功
     */
    @RequiresRoles(value = {RoleNames.EMPLOYEE,RoleNames.MANAGER},logical = Logical.OR)
    @RequiresPermissions(value = {PermissionNames.WRITE})
    @RequestMapping(value = "/updateEmployee.do")
    public boolean updateEmployee(String birthday,String email, String identifyNumber) throws Exception {
        if(birthday==null||email==null||identifyNumber==null){
            throw new RequestParameterException("请求参数不正确");
        }
        Employee employee=retrieveService.retrieveEmployeeByIdentifyNumber(identifyNumber);
        employee.setBirthday(DateUtil.parse(birthday));
        employee.setEmail(email);
        return updateService.updateEmployee(employee);
    }

    /**
     * 请求描述：员工自己查询员工基本信息
     * 请求地址：  /selfRetrieveEmployee.do
     * 请求参数：
     * 返回值：Employee
     */
    @RequiresRoles(value = {RoleNames.EMPLOYEE,RoleNames.MANAGER},logical = Logical.OR)
    @RequiresPermissions(value = {PermissionNames.READ})
    @RequestMapping("/selfRetrieveEmployee.do")
    public Employee selfRetrieveEmployee(){
        Session session = SecurityUtils.getSubject().getSession(false);
        int accountNumber = Integer.parseInt(session.getAttribute(AttributeNames.ACCOUNT_NUMBER).toString());
        return retrieveService.retrieveEmployeeByAccountNumber(accountNumber);
    }
    /**
     * 请求描述：员工自己查询员工基本信息
     * 请求地址：  /selfRetrieveEmployeePage.do
     * 请求参数：page 页码：1，2，3，。。。
     * 返回值：Employee
     */
    @RequiresRoles(value = {RoleNames.EMPLOYEE,RoleNames.MANAGER},logical = Logical.OR)
    @RequiresPermissions(value = {PermissionNames.READ})
    @RequestMapping("/selfRetrieveEmployeePage.do")
    public Employee selfRetrieveEmployeePage(Integer page){
        if(page==null){
            throw new RequestParameterException("请求参数不正确");
        }
        Session session = SecurityUtils.getSubject().getSession(false);
        int accountNumber = Integer.parseInt(session.getAttribute(AttributeNames.ACCOUNT_NUMBER).toString());
        return retrieveService.retrieveEmployeeByAccountNumberPage(accountNumber,page);
    }
}
