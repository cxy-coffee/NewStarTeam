package com.rookiestar.starmanager.entity.company;

import com.rookiestar.starmanager.entity.department.Department;
import com.rookiestar.starmanager.entity.experience.Experience;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Entity class that describe a company
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
@Entity
public class Company implements Serializable {
    private static final long serialVersionUID = 3L;
    @Id
    private int companyId;
    @Column(nullable = false)
    private String name;
    @Transient
    private List<Experience> experiences;
    @Transient
    private List<Department> departments;

    public Company() {
    }

    public Company(int companyId, String name, List<Experience> experiences, List<Department> departments) {
        this.companyId = companyId;
        this.name = name;
        this.experiences = experiences;
        this.departments = departments;
    }

    public Company(Company company,Department...departments){
        this.companyId = company.getCompanyId();
        this.name = company.getName();
        if(company.getExperiences()!=null){
            this.experiences = new ArrayList<>();
            this.experiences.addAll(company.getExperiences());
        }
        this.departments = new ArrayList<>();
        Collections.addAll(this.departments,departments);
    }

    public Company(Company company, Experience...experiences){
        this.companyId = company.getCompanyId();
        this.name = company.getName();
        if(company.getDepartments()!=null){
            this.departments = new ArrayList<>();
            this.departments.addAll(company.getDepartments());
        }
        this.experiences = new ArrayList<>();
        Collections.addAll(this.experiences,experiences);
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", name='" + name + '\'' +
                ", experiences=" + experiences +
                ", departments=" + departments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Company)) {
            return false;
        }
        Company company = (Company) o;
        return companyId == company.companyId && name.equals(company.name) && Objects.equals(experiences, company.experiences) && Objects.equals(departments, company.departments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId);
    }
}
