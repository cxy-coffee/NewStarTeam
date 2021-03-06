package com.rookiestar.starmanager.entity.assessment;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Entity class that describe the Primary Key of Assessment
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
public class AssessmentPrimaryKey implements Serializable {
    private int accountNumber;
    private int companyId;
    private Date startTime;

    public AssessmentPrimaryKey() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AssessmentPrimaryKey)) {
            return false;
        }
        AssessmentPrimaryKey that = (AssessmentPrimaryKey) o;
        return accountNumber == that.accountNumber && companyId == that.companyId && startTime.equals(that.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, companyId, startTime);
    }
}
