package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.Experience;
import com.rookiestar.starmanager.util.DataBaseUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExperienceRepositoryTest extends BaseTest {
    @Autowired
    private ExperienceRepository experienceRepository;

    private final Map<Integer,Experience> experienceMap;

    public ExperienceRepositoryTest() throws Exception{
        experienceMap = DataBaseUtil.getInstance().getExperienceMap();
    }

    @Test
    @Transactional
    public void findAllByAccountNumberTest() throws Exception{
        DataBaseUtil.getInstance().initExperience(experienceRepository);

        List<Experience> experiences = experienceRepository.findAllByAccountNumber(5);

        //System.out.println("experiences:"+experiences);
        /*
        experiences:[Experience{accountNumber=5, companyId=1, departmentId=2, positionId=1, jobNumber=1521, startTime=Sun Jan 10 00:00:00 CST 2010, endTime=null, isEnd=false, assessment=null}, Experience{accountNumber=5, companyId=2, departmentId=2, positionId=1, jobNumber=2521, startTime=Sun Jan 10 00:00:00 CST 2010, endTime=null, isEnd=true, assessment=null}]
         */

        List<Experience> actualExperiences = new ArrayList<>();
        actualExperiences.add(experienceMap.get(5121));
        actualExperiences.add(experienceMap.get(5221));

        //System.out.println("actualExperiences:"+actualExperiences);
        /*
        actualExperiences:[Experience{accountNumber=5, companyId=1, departmentId=2, positionId=1, jobNumber=1521, startTime=Sun Jan 10 00:00:00 CST 2010, endTime=null, isEnd=false, assessment=null}, Experience{accountNumber=5, companyId=2, departmentId=2, positionId=1, jobNumber=2521, startTime=Sun Jan 10 00:00:00 CST 2010, endTime=null, isEnd=true, assessment=null}]
         */

        Assert.assertEquals(experiences,actualExperiences);
    }

    @Test
    @Transactional
    public void findByAccountNumberAndCompanyIdAndDepartmentIdAndPositionIdTest() throws Exception{
        DataBaseUtil.getInstance().initExperience(experienceRepository);

        Experience experience = experienceRepository.findByAccountNumberAndCompanyIdAndDepartmentIdAndPositionId(5,1,2,1);

        //System.out.println("experience:"+experience);
        /*
        experience:Experience{accountNumber=5, companyId=1, departmentId=2, positionId=1, jobNumber=1521, startTime=Sun Jan 10 00:00:00 CST 2010, endTime=null, isEnd=false, assessment=null}
         */

        Experience actualExperience = experienceMap.get(5121);

        //System.out.println("actualExperience:"+actualExperience);
        /*
        actualExperience:Experience{accountNumber=5, companyId=1, departmentId=2, positionId=1, jobNumber=1521, startTime=Sun Jan 10 00:00:00 CST 2010, endTime=null, isEnd=false, assessment=null}
         */

        Assert.assertEquals(experience,actualExperience);
    }

}