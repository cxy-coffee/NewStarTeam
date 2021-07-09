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
}
