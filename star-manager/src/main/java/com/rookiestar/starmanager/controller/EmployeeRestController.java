package com.rookiestar.starmanager.controller;

import com.rookiestar.starmanager.constant.AttributeNames;
import com.rookiestar.starmanager.constant.PermissionNames;
import com.rookiestar.starmanager.constant.RoleNames;
import com.rookiestar.starmanager.entity.employee.Employee;
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

import javax.persistence.criteria.CriteriaBuilder;

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
     * 请求参数：String name 姓名, String birthday 出生年月日（yyyy-MM-dd）,String gender 性别,String email 邮箱,String identifyNumber 身份证号,String password 密码
     * 返回值：boolean 更新是否成功
     */
    @RequiresRoles(value = {RoleNames.EMPLOYEE,RoleNames.MANAGER},logical = Logical.OR)
    @RequiresPermissions(value = {PermissionNames.WRITE})
    @RequestMapping(value = "/updateEmployee.do")
    public boolean updateEmployee(String name, String birthday, String gender, String email, String identifyNumber, String password) throws Exception {
        Employee employee = new Employee(name, DateUtil.parse(birthday),gender,email,identifyNumber,0,password,null);
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



}
