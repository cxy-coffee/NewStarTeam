package com.rookiestar.starmanager.entity.employee;

import com.rookiestar.starmanager.entity.experience.Experience;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Entity class that describe an Employee
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
@Entity
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Date birthday;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false,unique = true)
    private String identifyNumber;

    @Id()
    private int accountNumber;
    @Column(nullable = false)
    private String password;

    @Transient
    private List<Experience> experiences;

    public Employee() {
    }

    public Employee(String name, Date birthday, String gender, String email, String identifyNumber, int accountNumber, String password, List<Experience> experiences) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.email = email;
        this.identifyNumber = identifyNumber;
        this.accountNumber = accountNumber;
        this.password = password;
        this.experiences = experiences;
    }

    public Employee(Employee employee, Experience...experiences){
        this.name = employee.getName();
        this.birthday = employee.getBirthday();
        this.gender = employee.getGender();
        this.email = employee.getEmail();
        this.identifyNumber = employee.getIdentifyNumber();
        this.accountNumber = employee.getAccountNumber();
        this.password = employee.getPassword();
        this.experiences = new ArrayList<>();
        Collections.addAll(this.experiences, experiences);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public String getIdentifyNumber() {
        return identifyNumber;
    }

    public void setIdentifyNumber(String identifyNumber) {
        this.identifyNumber = identifyNumber;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", identifyNumber='" + identifyNumber + '\'' +
                ", accountNumber=" + accountNumber +
                ", password='" + password + '\'' +
                ", experiences=" + experiences +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return accountNumber == employee.accountNumber && name.equals(employee.name) && birthday.equals(employee.birthday) && gender.equals(employee.gender) && email.equals(employee.email) && identifyNumber.equals(employee.identifyNumber) && password.equals(employee.password) && Objects.equals(experiences, employee.experiences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifyNumber, accountNumber);
    }
}
