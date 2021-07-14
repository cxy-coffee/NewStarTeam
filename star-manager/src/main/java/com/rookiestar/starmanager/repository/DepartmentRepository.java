package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.entity.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repository class that access to the table department
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    /**
     * find a department by its primary key
     * @param companyId the companyId of the department to find
     * @param departmentId the departmentId of the department to find
     * @return the department which matches all the params
     */
    Department findByCompanyIdAndDepartmentId(int companyId,int departmentId);
}
