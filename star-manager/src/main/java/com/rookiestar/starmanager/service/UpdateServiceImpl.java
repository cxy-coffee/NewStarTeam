package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.entity.Assessment;
import com.rookiestar.starmanager.entity.Employee;
import com.rookiestar.starmanager.repository.AssessmentRepository;
import com.rookiestar.starmanager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chris
 * @date 2021/7/12
 * @time 15:03
 */
@Service
public class UpdateServiceImpl implements UpdateService{
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    AssessmentRepository assessmentRepository;
    @Override
    public boolean updateEmployee(Employee employee) {
        employeeRepository.save(employee);
        return true;
    }

    @Override
    public boolean updateAssessment(Assessment assessment) {
        assessmentRepository.save(assessment);
        return true;
    }

}
