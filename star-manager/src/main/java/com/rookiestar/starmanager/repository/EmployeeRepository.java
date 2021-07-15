package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.entity.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository class that access to the table employee
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    /**
     * find all employees of a company
     *
     * @param companyId companyId
     * @return List<Employee>
     */
    @Query("select emp from Employee emp join Experience exp on emp.accountNumber=exp.accountNumber where exp.companyId=?1")
    List<Employee> findAllEmployeesByCompany(int companyId);

    /**
     * find present employees of a company
     *
     * @param companyId companyId
     * @return List<Employee>
     */
    @Query("select emp from Employee emp join Experience exp on emp.accountNumber=exp.accountNumber where exp.companyId=?1 and exp.isEnd=false")
    List<Employee> findPresentEmployeesByCompany(int companyId);

    /**
     * find the max accountNumber
     *
     * @return int
     */
    @Query("select max(emp.accountNumber) from Employee emp")
    int findMaxAccountNumber();

    /**
     * find employee by accountNumber
     *
     * @param accountNumber accountNumber
     * @return Employee
     */
    Employee findByAccountNumber(int accountNumber);

    /**
     * Get employees by name
     * @param name the name/part of the name of the employees to find
     * @return employees whose name containing String name
     */
    List<Employee> findEmployeesByNameContaining(String name);

    /**
     * Get employee by identify number
     * @param identifyNumber the identify number of the employee to find
     * @return the employee whose identify number matches the param
     */
    Employee findByIdentifyNumber(String identifyNumber);

    /**
     * Get employees by gender
     * @param gender the gender of the employees to find
     * @return the list of employees whose gender matches the param
     */
    List<Employee> findEmployeesByGender(String gender);

    /**
     * get employee by email
     * @param email the email address of the employee to find
     * @return the employee whose email matches the param
     */
    Employee findByEmail(String email);


    @Query("select emp from Employee emp join Experience exp on emp.accountNumber=exp.accountNumber where exp.companyId=?1 and exp.departmentId=?2")
    List<Employee> findByCompanyIdAndDepartmentId(int companyId,int departmentId);

    @Query("select emp from Employee emp join Experience exp on emp.accountNumber=exp.accountNumber where exp.companyId=?1 and exp.departmentId=?2 and exp.positionId=?3")
    List<Employee> findByCompanyIdAndDepartmentIdAndPositionId(int companyId,int departmentId,int positionId);
}
