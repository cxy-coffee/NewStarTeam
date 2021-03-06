package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.entity.assessment.Assessment;
import com.rookiestar.starmanager.entity.company.Company;
import com.rookiestar.starmanager.entity.company.CompanyToReview;
import com.rookiestar.starmanager.entity.department.Department;
import com.rookiestar.starmanager.entity.employee.Employee;
import com.rookiestar.starmanager.entity.employee.JobHunting;
import com.rookiestar.starmanager.entity.experience.Experience;
import com.rookiestar.starmanager.entity.position.Position;
import com.rookiestar.starmanager.repository.*;
import com.rookiestar.starmanager.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private JobHuntingRepository jobHuntingRepository;
    @Autowired
    private CompanyToReviewRepository companyToReviewRepository;

    /**
     * 查询员工
     */
    @Override
    public List<Employee> retrieveAllEmployeesByCompany(int companyId) {
        List<Employee> employees = employeeRepository.findAllEmployeesByCompany(companyId);
        perfectEmployees(employees);
        return employees;
    }
    @Override
    public List<Employee> retrievePresentEmployeesByCompany(int companyId) {
        List<Employee> employees = employeeRepository.findPresentEmployeesByCompany(companyId);
        perfectPresentEmployees(employees,companyId);
        return employees;
    }
    @Override
    public List<Employee> retrievePresentEmployeesByCompanyPage(int companyId, int page) {
        List<Employee> employees = employeeRepository.findPresentEmployeesByCompany(companyId,PageUtil.getPageable(page-1,"accountNumber")).getContent();
        perfectPresentEmployees(employees,companyId);
        return employees;
    }

    @Override
    public List<Employee> retrieveEmployeesByName(String name) {
        List<Employee> employees=employeeRepository.findEmployeesByNameContaining(name);
        perfectEmployees(employees);
        return employees;
    }
    @Override
    public List<Employee> retrieveEmployeesByCompanyIdAndName(int companyId,String name) {
        List<Employee> employees=employeeRepository.findByCompanyIdAndName(companyId,name);
        perfectPresentEmployees(employees,companyId);
        return employees;
    }
    @Override
    public List<Employee> retrieveEmployeesByCompanyIdAndNamePage(int companyId, String name, int page) {
        List<Employee> employees=employeeRepository.findByCompanyIdAndName(companyId,name,PageUtil.getPageable(page-1,"accountNumber")).getContent();
        perfectPresentEmployees(employees,companyId);
        return employees;
    }

    @Override
    public Employee retrieveEmployeeByIdentifyNumber(String identifyNumber) {
        Employee employee=employeeRepository.findByIdentifyNumber(identifyNumber);
        perfectEmployee(employee);
        return employee;
    }
    @Override
    public Employee retrieveEmployeesByCompanyIdAndIdentifyNumber(int companyId, String identifyNumber) {
        Employee employee=employeeRepository.findByCompanyIdAndIdentifyNumber(companyId,identifyNumber);
        perfectPresentEmployee(employee,companyId);
        return employee;
    }

    @Override
    public List<Employee> retrieveEmployeesByGender(String gender) {
        List<Employee> employees=employeeRepository.findEmployeesByGender(gender);
        perfectEmployees(employees);
        return employees;
    }
    @Override
    public List<Employee> retrieveEmployeesByCompanyIdAndGender(int companyId, String gender) {
        List<Employee> employees=employeeRepository.findByCompanyIdAndGender(companyId,gender);
        perfectPresentEmployees(employees,companyId);
        return employees;
    }
    @Override
    public List<Employee> retrieveEmployeesByCompanyIdAndGender(int companyId, String gender, int page) {
        List<Employee> employees=employeeRepository.findByCompanyIdAndGender(companyId,gender,PageUtil.getPageable(page-1,"accountNumber")).getContent();
        perfectPresentEmployees(employees,companyId);
        return employees;
    }

    @Override
    public Employee retrieveEmployeeByEmail(String email) {
        Employee employee=employeeRepository.findByEmail(email);
        perfectEmployee(employee);
        return employee;
    }
    @Override
    public Employee retrieveEmployeesByCompanyIdAndEmail(int companyId, String email) {
        Employee employee=employeeRepository.findByCompanyIdAndEmail(companyId,email);
        perfectPresentEmployee(employee,companyId);
        return employee;
    }

    @Override
    public List<Employee> retrieveEmployeesByCompanyIdAndDepartmentId(int companyId, int departmentId) {
        List<Employee> employees=employeeRepository.findByCompanyIdAndDepartmentId(companyId,departmentId);
        perfectPresentEmployees(employees,companyId);
        return employees;
    }

    @Override
    public List<Employee> retrieveEmployeesByCompanyIdAndDepartmentIdAndPositionId(int companyId, int departmentId, int positionId) {
        List<Employee> employees=employeeRepository.findByCompanyIdAndDepartmentIdAndPositionId(companyId,departmentId,positionId);
        perfectPresentEmployees(employees,companyId);
        return employees;
    }

    @Override
    public Employee retrieveEmployeeByAccountNumber(int accountNumber) {
        Employee employee = employeeRepository.findByAccountNumber(accountNumber);
        perfectEmployee(employee);
        return employee;
    }
    @Override
    public Employee retrieveEmployeeByAccountNumberPage(int accountNumber,int page) {
        Employee employee = employeeRepository.findByAccountNumber(accountNumber);
        perfectEmployee(employee,page-1);
        return employee;
    }


    /**
     *返回员工评价
     */
    @Override
    public Assessment retrieveAssessmentByAccountNumberAndCompanyIdAndStartTime(int accountNumber, int companyId, Date startTime) {
        return assessmentRepository.findByAccountNumberAndCompanyIdAndStartTime(accountNumber,companyId,startTime);
    }


    /**
     *返回经历
     */
    @Override
    public Experience retrieveExperienceByAccountNumberAndCompanyIdAndStartTime(int accountNumber, int companyId, Date startTime) {
        Experience experience=experienceRepository.findByAccountNumberAndCompanyIdAndStartTime(accountNumber,companyId,startTime);
        perfectExperience(experience);
        return experience;
    }


    /**
     *返回部门
     */
    @Override
    public Department retrieveDepartmentByCompanyIdAndDepartmentId(int companyId, int departmentId) {
        Department department=departmentRepository.findByCompanyIdAndDepartmentId(companyId,departmentId);
        if(department!=null){
            perfectDepartment(department);
        }
        return department;
    }
    @Override
    public List<Department> retrieveDepartmentByCompanyId(int companyId) {
        List<Department> departments=departmentRepository.findByCompanyId(companyId);
        perfectDepartments(departments);
        return departments;
    }


    /**
     *返回职位
     */
    @Override
    public Position retrievePositionByCompanyIdAndDepartmentIdAndPositionId(int companyId, int departmentId, int positionId) {
        return positionRepository.findByCompanyIdAndDepartmentIdAndPositionId(companyId,departmentId,positionId);
    }
    @Override
    public List<Position> retrievePositionByCompanyIdAndDepartmentId(int companyId, int departmentId) {
        return positionRepository.findByCompanyIdAndDepartmentId(companyId,departmentId);
    }


    /**
     *返回待确认企业
     */
    @Override
    public List<CompanyToReview> retrieveAllCompanyToReview() {
        return companyToReviewRepository.findAll();
    }
    @Override
    public List<CompanyToReview> retrieveAllCompanyToReviewPage(int page) {
        return companyToReviewRepository.findAll(PageUtil.getPageable(page-1,"companyId")).getContent();
    }


    /**
     *返回企业
     */
    @Override
    public Company retrieveCompanyByCompanyId(int companyId) {
        Company company=companyRepository.findByCompanyId(companyId);
        perfectCompany(company);
        return company;
    }


    /**
     *返回求职
     */
    @Override
    public List<JobHunting> retrieveAllJobHuntings() {
        List<JobHunting> jobHuntings=jobHuntingRepository.findAllCurrentJobHunting();
        perfectJobHuntings(jobHuntings);
        return jobHuntings;
    }
    @Override
    public List<JobHunting> retrieveJobHuntingsByIdealPosition(String idealPosition) {
        List<JobHunting> jobHuntings=jobHuntingRepository.findCurrentJobHuntingsByIdealPosition(idealPosition);
        perfectJobHuntings(jobHuntings);
        return jobHuntings;
    }
    @Override
    public List<JobHunting> retrieveJobHuntingsByDegree(String degree) {
        List<JobHunting> jobHuntings=jobHuntingRepository.findCurrentJobHuntingsByDegree(degree);
        perfectJobHuntings(jobHuntings);
        return jobHuntings;
    }
    @Override
    public List<JobHunting> retrieveJobHuntingsByIdealPositionAndDegree(String idealPosition, String degree) {
        List<JobHunting> jobHuntings=jobHuntingRepository.findCurrentJobHuntingsByIdealPositionAndDegree(idealPosition,degree);
        perfectJobHuntings(jobHuntings);
        return jobHuntings;
    }
    @Override
    public JobHunting retrieveCurrentJobHuntingByAccountNumber(int accountNumber) {
        JobHunting jobHunting=jobHuntingRepository.findCurrentJobHuntingByAccountNumber(accountNumber);
        perfectJobHunting(jobHunting);
        return jobHunting;
    }
    @Override
    public JobHunting retrieveJobHuntingByAccountNumber(int accountNumber){
        JobHunting jobHunting=jobHuntingRepository.findJobHuntingByAccountNumber(accountNumber);
        perfectJobHunting(jobHunting);
        return jobHunting;
    }

    /**
     *完整化数据
     */
    private void perfectExperience(Experience experience){
        if(experience==null){
            return;
        }
        experience.setAssessment(assessmentRepository.findByAccountNumberAndCompanyIdAndStartTime(experience.getAccountNumber(),experience.getCompanyId(),experience.getStartTime()));
    }
    private void perfectExperiences(List<Experience> experiences){
        if(experiences==null){
            return;
        }
        for(Experience experience:experiences){
            perfectExperience(experience);
        }
    }
    private void perfectPresentEmployee(Employee employee ,int companyId){
        if(employee==null){
            return;
        }
        List<Experience> byCompanyIdAndAccountNumber = experienceRepository.findByCompanyIdAndAccountNumber(companyId, employee.getAccountNumber());
        byCompanyIdAndAccountNumber.removeIf(Experience::isIsEnd);
        employee.setExperiences(byCompanyIdAndAccountNumber);
        perfectExperiences(employee.getExperiences());
    }
    private void perfectPresentEmployees(List<Employee> employees ,int companyId){
        if(employees==null){
            return;
        }
        for (Employee employee:employees) {
            perfectPresentEmployee(employee,companyId);
        }
    }
    private void perfectEmployee(Employee employee){
        if(employee==null){
            return;
        }
        employee.setExperiences(experienceRepository.findAllByAccountNumber(employee.getAccountNumber()));
        perfectExperiences(employee.getExperiences());
    }
    private void perfectEmployee(Employee employee,int page){
        if(employee==null){
            return;
        }
        employee.setExperiences(experienceRepository.findAllByAccountNumber(employee.getAccountNumber(), PageUtil.getPageable(page,"companyId")).getContent());
        perfectExperiences(employee.getExperiences());
    }
    private void perfectEmployees(List<Employee> employees){
        if(employees==null){
            return;
        }
        for(Employee employee:employees){
            perfectEmployee(employee);
        }
    }
    private void perfectDepartment(Department department){
        if(department==null){
            return;
        }
        department.setPositions(positionRepository.findByCompanyIdAndDepartmentId(department.getCompanyId(),department.getDepartmentId()));

    }
    private void perfectCompany(Company company) {
        if(company==null){
            return;
        }
        company.setDepartments(departmentRepository.findByCompanyId(company.getCompanyId()));
        company.setExperiences(experienceRepository.findByCompanyId(company.getCompanyId()));
        perfectExperiences(company.getExperiences());
        perfectDepartments(company.getDepartments());
    }
    private void perfectDepartments(List<Department> departments){
        if(departments==null){
            return;
        }
        for(Department department:departments){
            perfectDepartment(department);
        }
    }
    private void perfectJobHuntings(List<JobHunting> jobHuntings) {
        if(jobHuntings==null){
            return;
        }
        for(JobHunting jobHunting:jobHuntings){
            perfectJobHunting(jobHunting);
        }
    }
    private void perfectJobHunting(JobHunting jobHunting) {
        if(jobHunting==null){
            return;
        }
        Employee employee=employeeRepository.findByAccountNumber(jobHunting.getAccountNumber());
        jobHunting.setEmail(employee.getEmail());
        jobHunting.setGender(employee.getGender());
        jobHunting.setName(employee.getName());
    }
}
