package com.rookiestar.starmanager.entity.position;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Objects;

/**
 * Entity class that describe a position
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
@IdClass(PositionPrimaryKey.class)
@Entity
public class Position implements Serializable {
    private static final long serialVersionUID = 5L;
    @Id
    private int companyId;
    @Id
    private int departmentId;
    @Id
    private int positionId;
    @Column(nullable = false)
    private String name;

    public Position() {
    }

    public Position(int companyId, int departmentId, int positionId, String name) {
        this.companyId = companyId;
        this.departmentId = departmentId;
        this.positionId = positionId;
        this.name = name;
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

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Position{" +
                "companyId=" + companyId +
                ", departmentId=" + departmentId +
                ", positionId=" + positionId +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        Position position = (Position) o;
        return companyId == position.companyId && departmentId == position.departmentId && positionId == position.positionId && name.equals(position.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, departmentId, positionId);
    }
}
