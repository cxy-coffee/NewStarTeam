package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.entity.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     * find present employees of a company with page
     *
     * @param companyId companyId
     * @param pageable pageable
     * @return Page<Employee>
     */
    @Query("select emp from Employee emp join Experience exp on emp.accountNumber=exp.accountNumber where exp.companyId=?1 and exp.isEnd=false")
    Page<Employee> findPresentEmployeesByCompany(int companyId,Pageable pageable);

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


    /**
     * get all employees in a department
     * @param companyId the companyId of the employees to find
     * @param departmentId the departmentId of the employees to find
     * @return the employees which match all the params
     */
    @Query("select emp from Employee emp join Experience exp on emp.accountNumber=exp.accountNumber where exp.companyId=?1 and exp.departmentId=?2")
    List<Employee> findByCompanyIdAndDepartmentId(int companyId,int departmentId);

    /**
     * get all employees at a position
     * @param companyId the companyId of the employees to find
     * @param departmentId the departmentId of the employees to find
     * @param positionId the positionId of the employees to find
     * @return the employees which match all the params
     */
    @Query("select emp from Employee emp join Experience exp on emp.accountNumber=exp.accountNumber where exp.companyId=?1 and exp.departmentId=?2 and exp.positionId=?3")
    List<Employee> findByCompanyIdAndDepartmentIdAndPositionId(int companyId,int departmentId,int positionId);

    /**
     * get all employees whose names contain a certain string and is now working in the company
     * @param companyId the companyId of the company that employees working in
     * @param name the name of the employees
     * @return the employees which match all the params
     */
    @Query("select emp from Employee emp join Experience exp on emp.accountNumber=exp.accountNumber where exp.companyId=?1 and emp.name like %?2% and exp.isEnd=false")
    List<Employee> findByCompanyIdAndName(int companyId,String name);

    /**
     * get all employees whose names contain a certain string and is now working in the company with page
     * @param companyId the companyId of the company that employees working in
     * @param name the name of the employees
     * @param pageable pageable
     * @return the employees which match all the params
     */
    @Query("select emp from Employee emp join Experience exp on emp.accountNumber=exp.accountNumber where exp.companyId=?1 and emp.name like %?2% and exp.isEnd=false")
    Page<Employee> findByCompanyIdAndName(int companyId,String name,Pageable pageable);

    /**
     * get all employees in a company that have same gender
     * @param companyId the companyId of the company that employees working in
     * @param gender the gender of the employees to find
     * @return the employees which match all the params
     */
    @Query("select emp from Employee emp join Experience exp on emp.accountNumber=exp.accountNumber where exp.companyId=?1 and emp.gender = ?2 and exp.isEnd=false")
    List<Employee> findByCompanyIdAndGender(int companyId,String gender);

    /**
     * get all employees in a company that have same gender with page
     * @param companyId the companyId of the company that employees working in
     * @param gender the gender of the employees to find
     * @param pageable pageable
     * @return the employees which match all the params
     */
    @Query("select emp from Employee emp join Experience exp on emp.accountNumber=exp.accountNumber where exp.companyId=?1 and emp.gender = ?2 and exp.isEnd=false")
    Page<Employee> findByCompanyIdAndGender(int companyId,String gender,Pageable pageable);

    /**
     * get an employee who's working in the company and having a email matches the param
     * @param companyId the companyId of the company that employees working in
     * @param email the email of the employee to find
     * @return the employee who matches all the params
     */
    @Query("select emp from Employee emp join Experience exp on emp.accountNumber=exp.accountNumber where exp.companyId=?1 and emp.email = ?2 and exp.isEnd=false")
    Employee findByCompanyIdAndEmail(int companyId,String email);

    /**
     * get an employee who's working in the company and having an identifyNumber matches the param
     * @param companyId the companyId of the company that employees working in
     * @param identifyNumber the identifyNumber of the employee to find
     * @return the employee who matches all the params
     */
    @Query("select emp from Employee emp join Experience exp on emp.accountNumber=exp.accountNumber where exp.companyId=?1 and emp.identifyNumber = ?2 and exp.isEnd=false")
    Employee findByCompanyIdAndIdentifyNumber(int companyId,String identifyNumber);
}
