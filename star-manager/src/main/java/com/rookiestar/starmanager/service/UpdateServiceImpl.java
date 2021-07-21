package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.entity.assessment.Assessment;
import com.rookiestar.starmanager.entity.department.Department;
import com.rookiestar.starmanager.entity.employee.Employee;
import com.rookiestar.starmanager.entity.employee.JobHunting;
import com.rookiestar.starmanager.entity.experience.Experience;
import com.rookiestar.starmanager.entity.position.Position;
import com.rookiestar.starmanager.repository.*;
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
    DepartmentRepository departmentRepository;
    @Autowired
    RetrieveService retrieveService;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    AssessmentRepository assessmentRepository;
    @Autowired
    ExperienceRepository experienceRepository;
    @Autowired
    PositionRepository positionRepository;
    @Autowired
    JobHuntingRepository jobHuntingRepository;
    @Override
    public boolean updateEmployee(Employee employee) {
        Employee employeeToUpdate=retrieveService.retrieveEmployeeByIdentifyNumber(employee.getIdentifyNumber());
        employeeToUpdate.setBirthday(employee.getBirthday());
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

    @Override
    public boolean updateDepartment(Department department) {
        departmentRepository.save(department);
        return true;
    }

    @Override
    public boolean updatePosition(Position position) {
        positionRepository.save(position);
        return true;
    }

    @Override
    public boolean updateJobHunting(JobHunting jobHunting) {
        jobHuntingRepository.updateJobHunting(jobHunting.getDegree(),jobHunting.getIdealPosition(),jobHunting.isJobHunting(), jobHunting.getAccountNumber());
        return true;
    }
}
