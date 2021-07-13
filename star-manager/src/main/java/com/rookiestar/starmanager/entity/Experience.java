package com.rookiestar.starmanager.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Entity class that describe an experience
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
@IdClass(value = ExperiencePrimaryKey.class)
@Entity
public class Experience implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    private int accountNumber;
    @Id
    private int companyId;
    @Column(nullable = false)
    private int departmentId;
    @Column(nullable = false)
    private int positionId;
    @Column(nullable = false)
    private int jobNumber;
    @Id
    private Date startTime;
    @Column
    private Date endTime;
    @Column(nullable = false)
    private boolean isEnd;

    @Transient
    private Assessment assessment;

    public Experience() {
    }

    public Experience(int accountNumber, int companyId, int departmentId, int positionId, int jobNumber, Date startTime, Date endTime, boolean isEnd, Assessment assessment) {
        this.accountNumber = accountNumber;
        this.companyId = companyId;
        this.departmentId = departmentId;
        this.positionId = positionId;
        this.jobNumber = jobNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isEnd = isEnd;
        this.assessment = assessment;
    }

    public Experience(int accountNumber, int companyId, int departmentId, int positionId, int jobNumber, Date startTime, Date endTime, boolean isEnd) {
        this.accountNumber = accountNumber;
        this.companyId = companyId;
        this.departmentId = departmentId;
        this.positionId = positionId;
        this.jobNumber = jobNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isEnd = isEnd;
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

    public int getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(int jobNumber) {
        this.jobNumber = jobNumber;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public boolean isIsEnd() {
        return isEnd;
    }

    public void setIsEnd(boolean end) {
        this.isEnd = end;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "accountNumber=" + accountNumber +
                ", companyId=" + companyId +
                ", departmentId=" + departmentId +
                ", positionId=" + positionId +
                ", jobNumber=" + jobNumber +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", end=" + isEnd +
                ", assessment=" + assessment +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Experience)) {
            return false;
        }
        Experience that = (Experience) o;
        return accountNumber == that.accountNumber && companyId == that.companyId && departmentId == that.departmentId && positionId == that.positionId && jobNumber == that.jobNumber && isEnd == that.isEnd && startTime.equals(that.startTime) && Objects.equals(endTime, that.endTime) && Objects.equals(assessment, that.assessment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, companyId, startTime);
    }
}
