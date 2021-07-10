package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Query("select emp from Employee emp join Experience exp on emp.accountNumber=exp.accountNumber where exp.companyId=?1")
    List<Employee> findAllEmployeesByCompany(int companyId);

    @Query("select emp from Employee emp join Experience exp on emp.accountNumber=exp.accountNumber where exp.companyId=?1 and exp.isEnd=false")
    List<Employee> findPresentEmployeesByCompany(int companyId);

    @Query("select max(emp.accountNumber) from Employee emp")
    int findMaxAccountNumber();

    Employee findByAccountNumber(int accountNumber);

    /**
     * Get employees by name
     * @param name
     * @return employees whose name containing String name
     */
    List<Employee> findEmployeesByNameContaining(String name);

    /**
     * Get employee by identify number
     * @param identifyNumber
     * @return
     */
    Employee findByIdentifyNumber(String identifyNumber);

    /**
     * Get employees by gender
     * @param gender
     * @return
     */
    List<Employee> findEmployeesByGender(String gender);
}
