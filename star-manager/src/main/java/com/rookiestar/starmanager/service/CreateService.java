package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.entity.Employee;
import com.rookiestar.starmanager.entity.Experience;

public interface CreateService {
    Employee registerEmployee(Employee employee);
    Experience hireEmployee(Experience experience) throws Exception;
}
