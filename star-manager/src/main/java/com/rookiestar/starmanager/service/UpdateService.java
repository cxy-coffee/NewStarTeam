package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.entity.Employee;

/**
 * @author chris
 * @date 2021/7/12
 * @time 15:01
 */
public interface UpdateService {
    /**
     * 更新员工信息
     * @param employee
     * @return
     */
    boolean updateEmployee(Employee employee);
}
