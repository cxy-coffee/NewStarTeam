package com.rookiestar.starmanager.controller;


import com.rookiestar.starmanager.constant.PermissionNames;
import com.rookiestar.starmanager.constant.RoleNames;
import com.rookiestar.starmanager.entity.company.Company;
import com.rookiestar.starmanager.entity.companymanager.CompanyManager;
import com.rookiestar.starmanager.exception.RequestParameterException;
import com.rookiestar.starmanager.rabbit.MessageProducer;
import com.rookiestar.starmanager.entity.company.CompanyToReview;
import com.rookiestar.starmanager.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller class that handle the request of manager
 *
 * @author 曹向阳
 * @date 2021/7/14
 */
@RestController
public class ManagerRestController {
    @Autowired
    private CreateService createService;
    @Autowired
    private DeleteService deleteService;
    @Autowired
    private RetrieveService retrieveService;
    @Autowired
    private MessageProducer messageProducer;



    /**
     * 请求描述：管理员审核企业注册的请求
     * 请求地址：  /confirmCompanyRegisterApply.do
     * 请求参数：int companyId 企业Id, String name 企业名, String legalRepresentativeName 法定代表名, String email 企业邮箱, String address 企业地址, String phone 企业电话
     * 返回值：Company 企业
     */
    @RequiresRoles(value = {RoleNames.MANAGER})
    @RequiresPermissions(value = {PermissionNames.WRITE})
    @RequestMapping(value = "/confirmCompanyRegisterApply.do")
    public Company confirmCompanyRegisterApply(Integer companyId, String name, String legalRepresentativeName, String email, String address, String phone){
        if(companyId==null||name==null||legalRepresentativeName==null||email==null||address==null||phone==null){
            throw new RequestParameterException("请求参数不正确");
        }
        deleteService.deleteCompanyToReviewByCompanyId(companyId);
        Company company=new Company(name,legalRepresentativeName,email,address,phone);
        Company newCompany=createService.registerCompany(company);

        CompanyManager companyManager = createService.initCompanyManager(newCompany);
        String content = "您的公司注册申请已被管理员通过，您的公司ID为："+company.getCompanyId()+"。\n您的临时管理员email为："+companyManager.getEmail()+",工号为："+companyManager.getJobNumber()+",密码为："+companyManager.getPassword();


        Map<String,String> contentMap = new HashMap<>(10);
        contentMap.put("to",email);
        contentMap.put("subject","注册成功通知");
        contentMap.put("content",content);

        messageProducer.sendNotice(contentMap);

        return newCompany;
    }

    /**
     * 请求描述： 获得所有待审核公司
     * 请求地址： /getCompanyToReview.do
     * 请求参数： 无
     * 返回值：   List<CompanyToReview> 所有待审核公司的列表
     */
    @RequiresRoles(value = {RoleNames.MANAGER})
    @RequiresPermissions(value = {PermissionNames.READ})
    @RequestMapping(value = "getCompanyToReview.do")
    public List<CompanyToReview> getCompanyToReview(){
        return retrieveService.retrieveAllCompanyToReview();
    }

    /**
     * 请求描述： 获得所有待审核公司
     * 请求地址： /getCompanyToReview.do
     * 请求参数： page 页码：1，2，3，。。。
     * 返回值：   List<CompanyToReview> 所有待审核公司的列表
     */
    @RequiresRoles(value = {RoleNames.MANAGER})
    @RequiresPermissions(value = {PermissionNames.READ})
    @RequestMapping(value = "getCompanyToReviewPage.do")
    public List<CompanyToReview> getCompanyToReviewPage(Integer page){
        if(page==null){
            throw new RequestParameterException("请求参数不正确");
        }
        return retrieveService.retrieveAllCompanyToReviewPage(page);
    }

}
