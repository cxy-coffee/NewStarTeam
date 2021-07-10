package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repository class that access to the table company
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
public interface CompanyRepository extends JpaRepository<Company,Long> {
    
}
