package com.rookiestar.starmanager.controller;

import com.rookiestar.starmanager.entity.Employee;
import com.rookiestar.starmanager.service.RetrieveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyRestController {

    @Autowired
    private RetrieveService retrieveService;

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
}
