package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.entity.Employee;

import java.util.List;

public interface RetrieveService {
    List<Employee> retrieveAllEmployeesByCompany(int companyId);
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
