package com.rookiestar.starmanager;

import com.rookiestar.starmanager.controller.CompanyRestControllerTest;
import com.rookiestar.starmanager.repository.EmployeeRepositoryTest;
import com.rookiestar.starmanager.repository.ExperienceRepositoryTest;
import com.rookiestar.starmanager.service.CreateServiceImpTest;
import com.rookiestar.starmanager.service.RetrieveServiceImpTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({EmployeeRepositoryTest.class
        , ExperienceRepositoryTest.class
        , RetrieveServiceImpTest.class
        , CreateServiceImpTest.class
        , CompanyRestControllerTest.class
})
public class TestSuits {
}
