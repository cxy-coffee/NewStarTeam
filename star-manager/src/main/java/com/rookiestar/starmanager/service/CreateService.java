package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.entity.Employee;
import com.rookiestar.starmanager.entity.Experience;

public interface CreateService {
    void createEmployee(Employee employee);
    void createExperience(Experience experience);
}
