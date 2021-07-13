package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.Experience;
import com.rookiestar.starmanager.util.DataBaseUtil;
import com.rookiestar.starmanager.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
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



}