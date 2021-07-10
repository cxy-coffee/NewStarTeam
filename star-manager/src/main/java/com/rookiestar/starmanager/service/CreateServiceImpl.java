package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.entity.Employee;
import com.rookiestar.starmanager.entity.Experience;
import com.rookiestar.starmanager.repository.EmployeeRepository;
import com.rookiestar.starmanager.repository.ExperienceRepository;
import com.rookiestar.starmanager.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Service class that handle create service
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
@Service
public class CreateServiceImpl implements CreateService {

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
    public Experience hireEmployee(Experience experience) throws Exception{
        experience.setJobNumber(generateJobNumber(experience));
        experience.setStartTime(DateUtil.format(new Date()));
        experience.setIsEnd(false);
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