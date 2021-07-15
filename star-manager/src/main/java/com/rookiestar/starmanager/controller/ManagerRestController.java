package com.rookiestar.starmanager.controller;

import com.rookiestar.starmanager.constant.PermissionNames;
import com.rookiestar.starmanager.constant.RoleNames;
import com.rookiestar.starmanager.entity.company.Company;
import com.rookiestar.starmanager.entity.company.CompanyToReview;
import com.rookiestar.starmanager.entity.department.Department;
import com.rookiestar.starmanager.entity.position.Position;
import com.rookiestar.starmanager.service.*;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class that handle the request of manager
 *
 * @author 曹向阳
 * @date 2021/7/14
 */
@RestController
public class ManagerRestController {
    @Autowired
    private UpdateService updateService;
    @Autowired
    private CreateService createService;
    @Autowired
    private DeleteService deleteService;
    @Autowired
    private EmailService emailService;

    /**
     * 请求描述：更新企业部门
     * 请求地址：  /updateDepartment.do
     * 请求参数：int companyId 企业Id,int departmentId 部门编号,String name 部门名
     * 返回值：boolean 是否更新成功
     */
    @RequiresRoles(value = {RoleNames.MANAGER})
    @RequiresPermissions(value = {PermissionNames.WRITE})
    @RequestMapping(value = "updateDepartment.do")
    public boolean updateDepartment(int companyId,int departmentId,String name){
        Department department = new Department(companyId, departmentId, name, null);
        return updateService.updateDepartment(department);
    }

    /**
     * 请求描述：更新部门的职位
     * 请求地址：  /updatePosition.do
     * 请求参数：int companyId 企业Id,int departmentId 部门编号,int positionId 职位编号,String name 职位名
     * 返回值：boolean 是否更新成功
     */
    @RequiresRoles(value = {RoleNames.MANAGER})
    @RequiresPermissions(value = {PermissionNames.WRITE})
    @RequestMapping(value = "updatePosition.do")
    public boolean updatePosition(int companyId,int departmentId,int positionId,String name){
        Position position = new Position(companyId, departmentId, positionId, name);
        return updateService.updatePosition(position);
    }

    /**
     * 请求描述：管理员审核企业注册的请求
     * 请求地址：  /confirmCompanyRegisterApply.do
     * 请求参数：int companyId 企业Id, String name 企业名, String legalRepresentativeName 法定代表名, String email 企业邮箱, String address 企业地址, String phone 企业电话
     * 返回值：Company 企业
     */
    @RequiresRoles(value = {RoleNames.MANAGER})
    @RequiresPermissions(value = {PermissionNames.WRITE})
    @RequestMapping(value = "confirmCompanyRegisterApply.do")
    public Company confirmCompanyRegisterApply(int companyId, String name, String legalRepresentativeName, String email, String address, String phone){
        deleteService.deleteCompanyToReviewByCompanyId(companyId);
        Company company=new Company(name,legalRepresentativeName,email,address,phone);
        Company newCompany=createService.registerCompany(company);
        String content = "您的公司注册申请已被管理员通过，您的公司ID为："+company.getCompanyId();
        emailService.sendSimpleEmail("2019302110260@whu.edu.cn","注册成功通知",content);
        return newCompany;
    }
}