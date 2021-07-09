package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.entity.Employee;
import com.rookiestar.starmanager.entity.Experience;
import com.rookiestar.starmanager.repository.EmployeeRepository;
import com.rookiestar.starmanager.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreateServiceImp implements CreateService {

    @Autowired
    private ExperienceRepository experienceRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee registerEmployee(Employee employee) {
        employee.setAccountNumber(generateAccountNumber(employee));
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Experience hireEmployee(Experience experience) {
        experience.setJobNumber(generateJobNumber(experience));
        experience.setStartTime(new Date());
        experience.setEnd(false);
        experienceRepository.save(experience);
        return experience;
    }

    private int generateJobNumber(Experience experience){
        String jonNumber = String.valueOf(experience.getAccountNumber()) +
                experience.getCompanyId() +
                experience.getDepartmentId() +
                experience.getPositionId();
        return Integer.parseInt(jonNumber);
    }

    private int generateAccountNumber(Employee employee){
        return employeeRepository.findMaxAccountNumber()+1;
    }
}
