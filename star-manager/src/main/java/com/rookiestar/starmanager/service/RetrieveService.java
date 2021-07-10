package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.entity.Employee;

import java.util.List;

/**
 * Service class that handle retrieve service
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
public interface RetrieveService {
    /**
     * retrieve all employees of the company with the companyId
     *
     * @param companyId companyId
     * @return List<Employee>
     */
    List<Employee> retrieveAllEmployeesByCompany(int companyId);

    /**
     * retrieve present employees of the company with the companyId
     *
     * @param companyId companyId
     * @return List<Employee>
     */
    List<Employee> retrievePresentEmployeesByCompany(int companyId);
}
