package com.rookiestar.starmanager.entity;

import java.io.Serializable;
import java.util.Objects;

public class ExperiencePK implements Serializable {
    private int accountNumber;
    private int companyId;
    private int departmentId;
    private int positionId;

    public ExperiencePK() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExperiencePK that = (ExperiencePK) o;
        return accountNumber == that.accountNumber &&
                companyId == that.companyId &&
                departmentId == that.departmentId &&
                positionId == that.positionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, companyId, departmentId, positionId);
    }
}
