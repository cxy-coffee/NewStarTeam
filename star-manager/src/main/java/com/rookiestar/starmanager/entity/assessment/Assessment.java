package com.rookiestar.starmanager.entity.assessment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


/**
 * Entity class that describe the assessment of an employee
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
@IdClass(AssessmentPrimaryKey.class)
@Entity
public class Assessment implements Serializable {
    private static final long serialVersionUID = 6L;

    @Id
    private int accountNumber;
    @Id
    private int companyId;
    @Id
    private Date startTime;
    @Column
    private String absenteeismRate;
    @Column
    private String performance;

    public Assessment() {
    }

    public Assessment(int accountNumber, int companyId, Date startTime, String absenteeismRate, String performance) {
        this.accountNumber = accountNumber;
        this.companyId = companyId;
        this.startTime = startTime;
        this.absenteeismRate = absenteeismRate;
        this.performance = performance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getAbsenteeismRate() {
        return absenteeismRate;
    }

    public void setAbsenteeismRate(String absenteeismRate) {
        this.absenteeismRate = absenteeismRate;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "accountNumber=" + accountNumber +
                ", companyId=" + companyId +
                ", startTime=" + startTime +
                ", absenteeismRate='" + absenteeismRate + '\'' +
                ", performance='" + performance + '\'' +
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
        Assessment that = (Assessment) o;
        return accountNumber == that.accountNumber &&
                companyId == that.companyId &&
                Objects.equals(startTime, that.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, companyId, startTime);
    }
}
