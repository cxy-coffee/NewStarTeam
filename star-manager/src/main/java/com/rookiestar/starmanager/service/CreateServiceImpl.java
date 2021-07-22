package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.entity.assessment.Assessment;
import com.rookiestar.starmanager.entity.company.Company;
import com.rookiestar.starmanager.entity.company.CompanyToReview;
import com.rookiestar.starmanager.entity.employee.Employee;
import com.rookiestar.starmanager.entity.employee.JobHunting;
import com.rookiestar.starmanager.entity.experience.Experience;
import com.rookiestar.starmanager.repository.*;
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
    @Autowired
    private AssessmentRepository assessmentRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyToReviewRepository companyToReviewRepository;
    @Autowired
    private JobHuntingRepository jobHuntingRepository;
    @Autowired
    private UpdateService updateService;

    @Override
    public Employee registerEmployee(Employee employee) {
        employee.setAccountNumber(generateAccountNumber(employee));
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Experience hireEmployee(Experience experience) throws Exception{
        Date date = DateUtil.format(new Date());

        experience.setJobNumber(generateJobNumber(experience));
        experience.setStartTime(date);
        experience.setIsEnd(false);
        experienceRepository.save(experience);

        Assessment assessment = new Assessment(experience.getAccountNumber(),experience.getCompanyId(),date,"","");
        assessmentRepository.save(assessment);

        return experience;
    }

    @Override
    public Company registerCompany(Company company) {
        company.setCompanyId(generateCompanyId(company));
        companyRepository.save(company);
        return company;
    }

    @Override
    public CompanyToReview addCompanyToReview(CompanyToReview companyToReview) {
        companyToReview.setCompanyId(generateCompanyToReviewId(companyToReview));
        companyToReviewRepository.save(companyToReview);
        return companyToReview;
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

    private int generateCompanyId(Company company){
        return companyRepository.findMaxCompanyId()+1;
    }

    private int generateCompanyToReviewId(CompanyToReview companyToReview){
        return companyToReviewRepository.findMaxCompanyToReviewId()+1;
    }

    @Override
    public boolean goJobHunting(int accountNumber,String idealPosition,String degree) {
        JobHunting jobHunting = jobHuntingRepository.findJobHuntingByAccountNumber(accountNumber);
        if(jobHunting==null){
            jobHunting=new JobHunting(degree,true,idealPosition,accountNumber);
            jobHuntingRepository.save(jobHunting);
        }else {
            jobHunting.setJobHunting(true);
            jobHunting.setDegree(degree);
            jobHunting.setIdealPosition(idealPosition);
            updateService.updateJobHunting(jobHunting);
        }
        return true;
    }
}