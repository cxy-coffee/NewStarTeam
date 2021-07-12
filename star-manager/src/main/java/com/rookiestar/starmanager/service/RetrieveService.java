package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.entity.Assessment;
import com.rookiestar.starmanager.entity.Employee;

import java.util.Date;
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
     * @param name the name of the employee to find
     * @return employees whose name containing String name
     */
    List<Employee> retrieveEmployeesByName(String name);

    /**
     * Get employee by identify number
     * @param identifyNumber the identify number of the employee to find
     * @return employee whose identify number matches the param
     */
    Employee retrieveEmployeeByIdentifyNumber(String identifyNumber);

    /**
     * Get employees by gender
     * @param gender the gender of the employees to find
     * @return employees whose gender matches the param
     */
    List<Employee> retrieveEmployeesByGender(String gender);

    /**
     * Get assessment by its primary key
     * @param accountNumber account number of the assessment to find
     * @param companyId company id of the assessment to find
     * @param startTime start time of the assessment to find
     * @return the assessment which matches all the params
     */
    Assessment retrieveAssessmentByAccountNumberAndCompanyIdAndStartTime(int accountNumber, int companyId, Date startTime);
}
