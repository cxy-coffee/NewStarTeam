package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.entity.employee.Employee;

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

    /**
     * Get employees by name
     * @param name
     * @return employees whose name containing String name
     */
    List<Employee> retrieveEmployeesByName(String name);

    /**
     * Get employee by identify number
     * @param identifyNumber
     * @return
     */
    Employee retrieveEmployeeByIdentifyNumber(String identifyNumber);

    /**
     * Get employees by gender
     * @param gender
     * @return
     */
    List<Employee> retrieveEmployeesByGender(String gender);
}
