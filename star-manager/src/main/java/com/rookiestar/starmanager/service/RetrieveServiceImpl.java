package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.entity.Employee;
import com.rookiestar.starmanager.repository.EmployeeRepository;
import com.rookiestar.starmanager.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class that handle retrieve service
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
@Service
public class RetrieveServiceImpl implements RetrieveService{
    @Autowired
    private ExperienceRepository experienceRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> retrieveAllEmployeesByCompany(int companyId) {
        List<Employee> employees = employeeRepository.findAllEmployeesByCompany(companyId);
        for(Employee employee:employees){
            employee.setExperiences(experienceRepository.findAllByAccountNumber(employee.getAccountNumber()));
        }
        return employees;
    }

    @Override
    public List<Employee> retrievePresentEmployeesByCompany(int companyId) {
        List<Employee> employees = employeeRepository.findPresentEmployeesByCompany(companyId);
        for(Employee employee:employees){
            employee.setExperiences(experienceRepository.findAllByAccountNumber(employee.getAccountNumber()));
        }
        return employees;
    }


    /**
     * Get employees by name
     * @param name
     * @return employees whose name containing String name
     */
    @Override
    public List<Employee> retrieveEmployeesByName(String name) {
        List<Employee> employees=employeeRepository.findEmployeesByNameContaining(name);
        for(Employee employee:employees){
            employee.setExperiences(experienceRepository.findAllByAccountNumber(employee.getAccountNumber()));
        }
        return employees;
    }

    @Override
    public Employee retrieveEmployeeByIdentifyNumber(String identifyNumber) {
        Employee employee=employeeRepository.findByIdentifyNumber(identifyNumber);
        employee.setExperiences(experienceRepository.findAllByAccountNumber(employee.getAccountNumber()));
        return employee;
    }

    @Override
    public List<Employee> retrieveEmployeesByGender(String gender) {
        List<Employee> employees=employeeRepository.findEmployeesByGender(gender);
        for(Employee employee:employees){
            employee.setExperiences(experienceRepository.findAllByAccountNumber(employee.getAccountNumber()));
        }
        return employees;
    }
}
