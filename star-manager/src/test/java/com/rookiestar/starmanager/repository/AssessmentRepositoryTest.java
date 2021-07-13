package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.assessment.Assessment;
import com.rookiestar.starmanager.util.DataBaseUtil;
import com.rookiestar.starmanager.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Test class that test AssessmentRepository
 *
 * @author 曹向阳
 * @date 2021/7/10
 */
public class AssessmentRepositoryTest extends BaseTest {
    @Autowired
    private AssessmentRepository assessmentRepository;

    private final Map<Integer,Assessment> assessmentMap;

    public AssessmentRepositoryTest() throws Exception{
        assessmentMap = DataBaseUtil.getInstance().getAssessmentMap();
    }

    @Test
    @Transactional
    public void findByAccountNumberAndCompanyIdAndStartTimeTest() throws Exception{
        DataBaseUtil.getInstance().initAssessment(assessmentRepository);

        Assessment assessment = assessmentRepository.findByAccountNumberAndCompanyIdAndStartTime(5,2, DateUtil.parse("2010-01-10"));

        Assessment actualAssessment = assessmentMap.get(52);

        Assert.assertEquals(actualAssessment,assessment);
    }
}