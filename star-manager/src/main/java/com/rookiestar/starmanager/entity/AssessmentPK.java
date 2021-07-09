package com.rookiestar.starmanager.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class AssessmentPK implements Serializable {
    private int accountNumber;
    private int companyId;
    private Date startTime;

    public AssessmentPK() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssessmentPK that = (AssessmentPK) o;
        return accountNumber == that.accountNumber &&
                companyId == that.companyId &&
                Objects.equals(startTime, that.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, companyId, startTime);
    }
}
