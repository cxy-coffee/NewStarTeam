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

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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

}