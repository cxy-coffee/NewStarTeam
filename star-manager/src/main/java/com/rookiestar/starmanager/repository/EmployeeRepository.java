package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.entity.Employee;
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
}
