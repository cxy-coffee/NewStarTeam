package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    
}
