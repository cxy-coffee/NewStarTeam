package com.rookiestar.starmanager.entity.companyManager;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entity class that describe the Primary Key of CompanyManager
 *
 * @author 曹向阳
 * @date 2021/7/12
 */

public class CompanyManagerPrimaryKey implements Serializable {
    private int companyId;
    private String email;
    private int jobNumber;

    public CompanyManagerPrimaryKey() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CompanyManagerPrimaryKey that = (CompanyManagerPrimaryKey) o;
        return companyId == that.companyId &&
                jobNumber == that.jobNumber &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, email, jobNumber);
    }
}
