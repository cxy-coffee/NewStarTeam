package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.entity.Assessment;
import com.rookiestar.starmanager.entity.Employee;
import com.rookiestar.starmanager.entity.Experience;

/**
 * @author chris
 * @date 2021/7/12
 * @time 15:01
 */
public interface UpdateService {
    /**
     * 更新员工信息
     * @param employee employee to update
     * @return update result, true for success and false for non-success
     */
    boolean updateEmployee(Employee employee);
    /**
     * 更新评价信息
     * @param assessment assessment to update
     * @return update result, true for success and false for non-success
     */
    boolean updateAssessment(Assessment assessment);

    boolean updateExperience(Experience experience);
}
