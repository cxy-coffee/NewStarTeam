package com.rookiestar.starmanager;

import com.rookiestar.starmanager.controller.*;
import com.rookiestar.starmanager.repository.AssessmentRepositoryTest;
import com.rookiestar.starmanager.repository.CompanyManagerRepositoryTest;
import com.rookiestar.starmanager.repository.EmployeeRepositoryTest;
import com.rookiestar.starmanager.repository.ExperienceRepositoryTest;
import com.rookiestar.starmanager.repository.*;
import com.rookiestar.starmanager.service.CreateServiceImplTest;
import com.rookiestar.starmanager.service.DeleteServiceImplTest;
import com.rookiestar.starmanager.service.RetrieveServiceImplTest;
import com.rookiestar.starmanager.service.UpdateServiceImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test class that test some test class together
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        EmployeeRepositoryTest.class
        , ExperienceRepositoryTest.class
        , AssessmentRepositoryTest.class
        , CompanyManagerRepositoryTest.class
        , CompanyToReviewRepositoryTest.class
        , ManagerRepositoryTest.class
        , DepartmentRepositoryTest.class
        , PositionRepositoryTest.class
        , CompanyRepositoryTest.class

        , RetrieveServiceImplTest.class
        , CreateServiceImplTest.class
        , UpdateServiceImplTest.class
        , DeleteServiceImplTest.class

        , CompanyRestControllerTest.class
        , CompanyRestControllerPageTest.class
        , EmployeeRestControllerTest.class
        , EmployeeRestControllerPageTest.class
        , ManagerRestControllerTest.class
        , LoginRestControllerTest.class
})
public class TestSuits {
}
