package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.entity.employee.Employee;
import com.rookiestar.starmanager.entity.experience.Experience;

/**
 * Service class that handle create service
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
public interface CreateService {
    /**
     * generate accountNumber for employee and save it into database
     * return employee with name,birthday,gender,email,identifyNumber,accountNumber,password
     *
     * @param employee employee with name,birthday,gender,email,identifyNumber,password
     * @return Employee
     */
    Employee registerEmployee(Employee employee);

    /**
     * generate jobNumber,startTime,end for experience and save it into database
     * return experience with accountNumber,companyId,departmentId,positionId,jobNumber,startTime,end
     *
     * @param experience experience with accountNumber,companyId,departmentId,positionId
     * @return Experience
     * @throws Exception any Exception
     */
    Experience hireEmployee(Experience experience) throws Exception;
}
