package com.rookiestar.starmanager.entity;

import java.io.Serializable;
import java.util.Objects;

public class PositionPK implements Serializable {
    private int companyId;
    private int departmentId;
    private int positionId;

    public PositionPK() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionPK that = (PositionPK) o;
        return companyId == that.companyId &&
                departmentId == that.departmentId &&
                positionId == that.positionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, departmentId, positionId);
    }
}
