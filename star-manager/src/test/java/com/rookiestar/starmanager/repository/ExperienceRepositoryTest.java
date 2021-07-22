package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.experience.Experience;
import com.rookiestar.starmanager.util.DataBaseUtil;
import com.rookiestar.starmanager.util.DataBaseUtilPages;
import com.rookiestar.starmanager.util.DateUtil;
import com.rookiestar.starmanager.util.PageUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Test class that test ExperienceRepository
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
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
        List<Experience> actualExperiences = new ArrayList<>();
        actualExperiences.add(experienceMap.get(5121));
        actualExperiences.add(experienceMap.get(5221));
        Assert.assertEquals(experiences,actualExperiences);
    }

    @Test
    @Transactional
    public void findAllByAccountNumberPageTest() throws Exception{
        DataBaseUtilPages.getInstance().initExperience(experienceRepository);

        List<Experience> actualExperiences = experienceRepository.findAllByAccountNumber(25, PageUtil.getPageable(1,"companyId")).getContent();

        Assert.assertEquals("[Experience{accountNumber=25, companyId=8, departmentId=2, positionId=1, jobNumber=82521, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=true, assessment=null}, Experience{accountNumber=25, companyId=9, departmentId=2, positionId=1, jobNumber=92521, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=true, assessment=null}, Experience{accountNumber=25, companyId=10, departmentId=2, positionId=1, jobNumber=102521, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=true, assessment=null}, Experience{accountNumber=25, companyId=11, departmentId=2, positionId=1, jobNumber=112521, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=true, assessment=null}, Experience{accountNumber=25, companyId=12, departmentId=2, positionId=1, jobNumber=122521, startTime=Fri Mar 12 00:00:00 CST 2077, endTime=null, end=true, assessment=null}]",actualExperiences.toString());
    }

    @Test
    @Transactional
    public void findByAccountNumberAndCompanyIdAndDepartmentIdAndPositionIdTest() throws Exception{
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        Experience experience = experienceRepository.findByAccountNumberAndCompanyIdAndDepartmentIdAndPositionId(5,1,2,1);
        Experience actualExperience = experienceMap.get(5121);
        Assert.assertEquals(experience,actualExperience);
    }

    @Test
    @Transactional
    public void findByAccountNumberAndCompanyIdAndStartTimeTest() throws Exception{
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        Experience experience=experienceRepository.findByAccountNumberAndCompanyIdAndStartTime(5,1, DateUtil.parse("2010-01-10"));
        Experience actualExperience=experienceMap.get(5121);
        Assert.assertEquals(experience,actualExperience);
    }

    @Test
    @Transactional
    public void findByCompanyIdTest()throws Exception{
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        List<Experience> experienceList=new ArrayList<>();
        Experience experience = new Experience(5, 1, 2, 1, 1521, DateUtil.parse("2010-01-10"), null, false);
        Experience experience1 = new Experience(6, 1, 2, 1, 1621, DateUtil.parse("2011-01-11"), null, false);
        Experience experience2 = new Experience(7, 1, 2, 1, 1721, DateUtil.parse("2012-01-12"), null, true);
        Experience experience3 = new Experience(8, 1, 2, 1, 1821, DateUtil.parse("2013-01-13"), null, true);
        experienceList.add(experience);
        experienceList.add(experience1);
        experienceList.add(experience2);
        experienceList.add(experience3);
        Assert.assertEquals(experienceList,experienceRepository.findByCompanyId(1));
    }

    @Test
    @Transactional
    public void findByCompanyIdAndAccountNumberTest()throws Exception{
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        List<Experience> experienceList=new ArrayList<>();
        Experience experience = new Experience(5, 1, 2, 1, 1521, DateUtil.parse("2010-01-10"), null, false);
        experienceList.add(experience);
        Assert.assertEquals(experienceList,experienceRepository.findByCompanyIdAndAccountNumber(1,5));
    }



}