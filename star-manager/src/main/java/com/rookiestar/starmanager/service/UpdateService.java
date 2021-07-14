package com.rookiestar.starmanager.service;


import com.rookiestar.starmanager.entity.assessment.Assessment;
import com.rookiestar.starmanager.entity.employee.Employee;
import com.rookiestar.starmanager.entity.experience.Experience;

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
    /**
     * 更新经历信息
     * @param experience experience to update
     * @return update result, true for success and false for non-success
     */
    boolean updateExperience(Experience experience);
}
