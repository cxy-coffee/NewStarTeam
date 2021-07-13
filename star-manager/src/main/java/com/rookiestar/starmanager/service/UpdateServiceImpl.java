package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.entity.Assessment;
import com.rookiestar.starmanager.entity.Employee;
import com.rookiestar.starmanager.entity.Experience;
import com.rookiestar.starmanager.repository.AssessmentRepository;
import com.rookiestar.starmanager.repository.EmployeeRepository;
import com.rookiestar.starmanager.repository.ExperienceRepository;
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
    RetrieveService retrieveService;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    AssessmentRepository assessmentRepository;
    @Autowired
    ExperienceRepository experienceRepository;
    @Override
    public boolean updateEmployee(Employee employee) {
        Employee employeeToUpdate=retrieveService.retrieveEmployeeByIdentifyNumber(employee.getIdentifyNumber());
        employeeToUpdate.setGender(employee.getGender());
        employeeToUpdate.setBirthday(employee.getBirthday());
        employeeToUpdate.setName(employee.getName());
        employeeToUpdate.setEmail(employee.getEmail());
        employeeToUpdate.setPassword(employee.getPassword());
        employeeRepository.save(employeeToUpdate);
        return true;
    }

    @Override
    public boolean updateAssessment(Assessment assessment) {
        assessmentRepository.save(assessment);
        return true;
    }

    @Override
    public boolean updateExperience(Experience experience) {
        experienceRepository.save(experience);
        return true;
    }

}
