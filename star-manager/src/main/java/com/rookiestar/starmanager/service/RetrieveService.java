package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.entity.Employee;

import java.util.List;

public interface RetrieveService {
    List<Employee> retrieveAllEmployeesByCompany(int companyId);
    List<Employee> retrievePresentEmployeesByCompany(int companyId);
}
