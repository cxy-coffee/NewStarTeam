package com.rookiestar.starmanager.entity.employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Objects;

/**
 * @author chris
 * @date 2021/7/21
 * @time 8:52
 */
@Entity
public class JobHunting {
    @Column(nullable = false)
    String degree;
    @Column(nullable = false)
    boolean jobHunting;
    @Column
    String idealPosition;
    @Id
    int accountNumber;

    @Transient
    String name;
    @Transient
    String email;
    @Transient
    String gender;

    public JobHunting(String degree, boolean jobHunting, String idealPosition, int accountNumber) {
        this.degree = degree;
        this.jobHunting = jobHunting;
        this.idealPosition = idealPosition;
        this.accountNumber = accountNumber;
    }

    public JobHunting(String degree, boolean jobHunting, String idealPosition, int accountNumber, String name, String email, String gender) {
        this.degree = degree;
        this.jobHunting = jobHunting;
        this.idealPosition = idealPosition;
        this.accountNumber = accountNumber;
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public JobHunting() {

    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public boolean isJobHunting() {
        return jobHunting;
    }

    public void setJobHunting(boolean jobHunting) {
        this.jobHunting = jobHunting;
    }

    public String getIdealPosition() {
        return idealPosition;
    }

    public void setIdealPosition(String idealPosition) {
        this.idealPosition = idealPosition;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JobHunting)) {
            return false;
        }
        JobHunting that = (JobHunting) o;
        return jobHunting == that.jobHunting && accountNumber == that.accountNumber && Objects.equals(degree, that.degree) && Objects.equals(idealPosition, that.idealPosition) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(degree, jobHunting, idealPosition, accountNumber, name, email, gender);
    }

    @Override
    public String toString() {
        return "JobHunting{" +
                "degree='" + degree + '\'' +
                ", jobHunting=" + jobHunting +
                ", idealPosition='" + idealPosition + '\'' +
                ", accountNumber=" + accountNumber +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}