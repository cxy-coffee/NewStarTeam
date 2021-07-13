package com.rookiestar.starmanager.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Entity class that describe a department of a company
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
@IdClass(DepartmentPrimaryKey.class)
@Entity
public class Department implements Serializable {
    private static final long serialVersionUID = 4L;
    @Id
    private int companyId;
    @Id
    private int departmentId;
    @Column(nullable = false)
    private String name;
    @Transient
    private List<Position> positions;

    public Department() {
    }

    public Department(int companyId, int departmentId, String name, List<Position> positions) {
        this.companyId = companyId;
        this.departmentId = departmentId;
        this.name = name;
        this.positions = positions;
    }
    public Department(Department department,Position...positions){
        this.companyId = department.getCompanyId();
        this.departmentId = department.getDepartmentId();
        this.name = department.getName();
        this.positions = new ArrayList<>();
        Collections.addAll(this.positions,positions);
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    @Override
    public String toString() {
        return "Department{" +
                "companyId=" + companyId +
                ", departmentId=" + departmentId +
                ", name='" + name + '\'' +
                ", positions=" + positions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Department)) {
            return false;
        }
        Department that = (Department) o;
        return companyId == that.companyId && departmentId == that.departmentId && name.equals(that.name) && Objects.equals(positions, that.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, departmentId);
    }
}
