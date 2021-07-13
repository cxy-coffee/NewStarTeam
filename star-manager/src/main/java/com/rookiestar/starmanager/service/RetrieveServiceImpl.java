package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.entity.employee.Employee;
import com.rookiestar.starmanager.entity.experience.Experience;
import com.rookiestar.starmanager.repository.AssessmentRepository;
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
    @Autowired
    private AssessmentRepository assessmentRepository;

    @Override
    public List<Employee> retrieveAllEmployeesByCompany(int companyId) {
        List<Employee> employees = employeeRepository.findAllEmployeesByCompany(companyId);
        perfectEmployees(employees);
        return employees;
    }

    @Override
    public List<Employee> retrievePresentEmployeesByCompany(int companyId) {
        List<Employee> employees = employeeRepository.findPresentEmployeesByCompany(companyId);
        perfectEmployees(employees);
        return employees;
    }

    @Override
    public List<Employee> retrieveEmployeesByName(String name) {
        List<Employee> employees=employeeRepository.findEmployeesByNameContaining(name);
        perfectEmployees(employees);
        return employees;
    }

    @Override
    public Employee retrieveEmployeeByIdentifyNumber(String identifyNumber) {
        Employee employee=employeeRepository.findByIdentifyNumber(identifyNumber);
        perfectEmployee(employee);
        return employee;
    }

    @Override
    public List<Employee> retrieveEmployeesByGender(String gender) {
        List<Employee> employees=employeeRepository.findEmployeesByGender(gender);
        perfectEmployees(employees);
        return employees;
    }

    /**
     *完整化数据
     */
    private void perfectExperience(Experience experience){
        experience.setAssessment(assessmentRepository.findByAccountNumberAndCompanyIdAndStartTime(experience.getAccountNumber(),experience.getCompanyId(),experience.getStartTime()));
    }
    private void perfectExperiences(List<Experience> experiences){
        for(Experience experience:experiences){
            perfectExperience(experience);
        }
    }
    private void perfectEmployee(Employee employee){
        employee.setExperiences(experienceRepository.findAllByAccountNumber(employee.getAccountNumber()));
        perfectExperiences(employee.getExperiences());
    }
    private void perfectEmployees(List<Employee> employees){
        for(Employee employee:employees){
            perfectEmployee(employee);
        }
    }
}
