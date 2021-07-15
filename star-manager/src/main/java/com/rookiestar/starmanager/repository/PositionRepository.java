package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.entity.position.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

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

    /**
     * delete a position by its primary key
     * @param companyId the companyId of the position to delete
     * @param departmentId the departmentId of the position to delete
     * @param positionId the positionId of the position to delete
     */
    void deletePositionByCompanyIdAndDepartmentIdAndPositionId(int companyId,int departmentId,int positionId);

    /**
     * find all the positions in a department
     * @param companyId the companyId of the positions to find
     * @param departmentId the departmentId of the positions to find
     * @return all the positions which match all the params
     */
    List<Position> findByCompanyIdAndDepartmentId(int companyId,int departmentId);
}
