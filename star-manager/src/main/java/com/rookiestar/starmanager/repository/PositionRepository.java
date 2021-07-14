package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.entity.position.Position;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class that access to the table position
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
public interface PositionRepository  extends JpaRepository<Position,Long> {
    /**
     * find a position by its primary key
     * @param companyId the companyId of the position to find
     * @param departmentId the departmentId of the position to find
     * @param positionId the positionId of the position to find
     * @return the position which matches all the params
     */
    Position findByCompanyIdAndDepartmentIdAndPositionId(int companyId,int departmentId,int positionId);
}
