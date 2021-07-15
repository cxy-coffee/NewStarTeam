package com.rookiestar.starmanager.service;



import com.rookiestar.starmanager.entity.assessment.Assessment;
import com.rookiestar.starmanager.entity.company.Company;
import com.rookiestar.starmanager.entity.department.Department;
import com.rookiestar.starmanager.entity.employee.Employee;
import com.rookiestar.starmanager.entity.experience.Experience;
import com.rookiestar.starmanager.entity.position.Position;

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

    /**
     * retrieve an experience by its primary key
     * @param accountNumber  the accountNumber of the experience to find
     * @param companyId the companyId of the experience to find
     * @param startTime the startTime of the experience to find
     * @return the experience which matches all the params
     */
    Experience retrieveExperienceByAccountNumberAndCompanyIdAndStartTime(int accountNumber, int companyId, Date startTime);

    /**
     * retrieve an employee by his/her email address
     * @param email the email address of the employee to find
     * @return the employee whose email matches the param
     */
    Employee retrieveEmployeeByEmail(String email);

    /**
     * retrieve the employee by its accountNumber
     * @param accountNumber the accountNumber of the employee to find
     * @return the employee whose accountNumber matches the param
     */
    Employee retrieveEmployeeByAccountNumber(int accountNumber);

    /**
     * retrieve a department by its primary key
     * @param companyId the companyId of the department to find
     * @param departmentId the departmentId of the department to find
     * @return the department which matches all the params
     */
    Department retrieveDepartmentByCompanyIdAndDepartmentId(int companyId,int departmentId);

    /**
     * retrieve a position by its primary key
     * @param companyId the companyId of the position to retrieve
     * @param departmentId the departmentId of the position to retrieve
     * @param positionId the positionId of the position to retrieve
     * @return the position which matches all the params
     */
    Position retrievePositionByCompanyIdAndDepartmentIdAndPositionId(int companyId, int departmentId, int positionId);

    /**
     * retrieve all the departments in a company
     * @param companyId the companyId of the departments to retrieve
     * @return all the departments which match all the params
     */
    List<Department> retrieveDepartmentByCompanyId(int companyId);

    /**
     * retrieve all the positions in a department
     * @param companyId the companyId of the positions to find
     * @param departmentId the departmentId of the positions to find
     * @return all the positions which match all the params
     */
    List<Position> retrievePositionByCompanyIdAndDepartmentId(int companyId, int departmentId);

    /**
     * retrieve all the employees in a department
     * @param companyId the companyId of the employees to find
     * @param departmentId the departmentId of the employees to find
     * @return the employees who match all the params
     */
    List<Employee> retrieveEmployeesByCompanyIdAndDepartmentId(int companyId, int departmentId);

    /**
     * retrieve all the employees at a position
     * @param companyId the companyId of the employees to find
     * @param departmentId the departmentId of the employees to find
     * @param positionId the positionId of the employees to find
     * @return all the employees who match all the params
     */
    List<Employee> retrieveEmployeesByCompanyIdAndDepartmentIdAndPositionId(int companyId, int departmentId, int positionId);
}
