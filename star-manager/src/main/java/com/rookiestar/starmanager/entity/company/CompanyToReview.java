package com.rookiestar.starmanager.entity.company;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author chris
 * @date 2021/7/13
 * @time 16:08
 */
@Entity
public class CompanyToReview implements Serializable {
    private static final long serialVersionUID = 9L;
    @Id
    private int companyId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String legalRepresentativeName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String phone;

    public CompanyToReview(String name, String legalRepresentativeName, String email, String address, String phone) {
        this.name = name;
        this.legalRepresentativeName = legalRepresentativeName;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public CompanyToReview() {
    }

    public CompanyToReview(int companyId, String name, String legalRepresentativeName, String email, String address, String phone) {
        this.companyId = companyId;
        this.name = name;
        this.legalRepresentativeName = legalRepresentativeName;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public static long getSerialVersionUid() {
        return serialVersionUID;
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

    public String getLegalRepresentativeName() {
        return legalRepresentativeName;
    }

    public void setLegalRepresentativeName(String legalRepresentativeName) {
        this.legalRepresentativeName = legalRepresentativeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CompanyToReview)) {
            return false;
        }
        CompanyToReview that = (CompanyToReview) o;
        return companyId == that.companyId && name.equals(that.name) && legalRepresentativeName.equals(that.legalRepresentativeName) && email.equals(that.email) && address.equals(that.address) && phone.equals(that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, name, legalRepresentativeName, email, address, phone);
    }

    @Override
    public String toString() {
        return "CompanyToReview{" +
                "companyId=" + companyId +
                ", name='" + name + '\'' +
                ", legalRepresentativeName='" + legalRepresentativeName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
