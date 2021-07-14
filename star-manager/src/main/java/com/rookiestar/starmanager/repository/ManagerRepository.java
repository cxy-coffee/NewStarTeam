package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.entity.manager.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class that access to the table manager
 *
 * @author 曹向阳
 * @date 2021/7/14
 */
public interface ManagerRepository extends JpaRepository<Manager,Long> {
    /**
     * find manager by accountNumber
     *
     * @param accountNumber accountNumber
     * @return Manager
     */
    Manager findByAccountNumber(int accountNumber);
}
