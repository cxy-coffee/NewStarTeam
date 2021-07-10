package com.rookiestar.starmanager.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entity class that describe the Primary Key of Department
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
public class DepartmentPrimaryKey implements Serializable {
    private int companyId;
    private int departmentId;

    public DepartmentPrimaryKey() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DepartmentPrimaryKey that = (DepartmentPrimaryKey) o;
        return companyId == that.companyId &&
                departmentId == that.departmentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, departmentId);
    }
}
