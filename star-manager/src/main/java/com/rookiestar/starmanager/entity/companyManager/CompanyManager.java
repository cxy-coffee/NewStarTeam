package com.rookiestar.starmanager.entity.companyManager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Objects;

/**
 * Entity class that describe the companyManager of a company
 *
 * @author 曹向阳
 * @date 2021/7/12
 */
@IdClass(value = CompanyManagerPrimaryKey.class)
@Entity
public class CompanyManager implements Serializable {
    private static final long serialVersionUID = 7L;

    @Id
    private int companyId;
    @Id
    private String email;
    @Id
    private int jobNumber;
    @Column(nullable = false)
    private String password;

    public CompanyManager() {
    }

    public CompanyManager(int companyId, String email, int jobNumber, String password) {
        this.companyId = companyId;
        this.email = email;
        this.jobNumber = jobNumber;
        this.password = password;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(int jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CompanyManager{" +
                "companyId=" + companyId +
                ", email='" + email + '\'' +
                ", jobNumber=" + jobNumber +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CompanyManager that = (CompanyManager) o;
        return companyId == that.companyId &&
                jobNumber == that.jobNumber &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, email, jobNumber);
    }
}
