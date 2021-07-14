package com.rookiestar.starmanager.entity.manager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Entity class that describe an Manager
 *
 * @author 曹向阳
 * @date 2021/7/14
 */
@Entity
public class Manager implements Serializable {
    private static final long serialVersionUID = 8L;

    @Column(nullable = false)
    private String managerName;
    @Column(nullable = false,unique = true)
    private String identifyNumber;
    @Column(nullable = false)
    private Date birthday;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String gender;

    @Id
    private int accountNumber;
    @Column(nullable = false)
    private String password;

    public Manager() {
    }

    public Manager(String managerName, String identifyNumber, Date birthday, String email, String gender, int accountNumber, String password) {
        this.managerName = managerName;
        this.identifyNumber = identifyNumber;
        this.birthday = birthday;
        this.email = email;
        this.gender = gender;
        this.accountNumber = accountNumber;
        this.password = password;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getIdentifyNumber() {
        return identifyNumber;
    }

    public void setIdentifyNumber(String identifyNumber) {
        this.identifyNumber = identifyNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    @Override
    public String toString() {
        return "Manager{" +
                "managerName='" + managerName + '\'' +
                ", identifyNumber='" + identifyNumber + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", accountNumber=" + accountNumber +
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
        Manager manager = (Manager) o;
        return accountNumber == manager.accountNumber &&
                Objects.equals(managerName, manager.managerName) &&
                Objects.equals(identifyNumber, manager.identifyNumber) &&
                Objects.equals(birthday, manager.birthday) &&
                Objects.equals(email, manager.email) &&
                Objects.equals(gender, manager.gender) &&
                Objects.equals(password, manager.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(managerName, identifyNumber, birthday, email, gender, accountNumber, password);
    }
}
