package com.rookiestar.starmanager.entity;

import java.io.Serializable;
import java.util.Objects;

public class DepartmentPK implements Serializable {
    private int companyId;
    private int departmentId;

    public DepartmentPK() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentPK that = (DepartmentPK) o;
        return companyId == that.companyId &&
                departmentId == that.departmentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, departmentId);
    }
}
