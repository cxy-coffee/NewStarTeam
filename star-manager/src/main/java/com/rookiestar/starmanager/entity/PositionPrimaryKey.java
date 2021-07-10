package com.rookiestar.starmanager.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entity class that describe the Primary Key of Position
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
public class PositionPrimaryKey implements Serializable {
    private int companyId;
    private int departmentId;
    private int positionId;

    public PositionPrimaryKey() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PositionPrimaryKey that = (PositionPrimaryKey) o;
        return companyId == that.companyId &&
                departmentId == that.departmentId &&
                positionId == that.positionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, departmentId, positionId);
    }
}
