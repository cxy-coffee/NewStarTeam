package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.assessment.Assessment;
import com.rookiestar.starmanager.entity.Assessment;
import com.rookiestar.starmanager.entity.Experience;
import com.rookiestar.starmanager.util.DataBaseUtil;
import com.rookiestar.starmanager.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author chris
 * @date 2021/7/12
 * @time 18:24
 */
public class AssessmentRepositoryTest extends BaseTest {
    @Autowired
    AssessmentRepository assessmentRepository;
    private final Map<Integer, Assessment> assessmentMap;
    public AssessmentRepositoryTest() throws Exception {
        assessmentMap= DataBaseUtil.getInstance().getAssessmentMap();
    }
    @Test
    @Transactional
    public void findByAccountNumberAndCompanyIdAndStartTimeTest()throws Exception{
        DataBaseUtil.getInstance().initAssessment(assessmentRepository);
        Assessment assessment= assessmentRepository.findByAccountNumberAndCompanyIdAndStartTime(5,1, DateUtil.parse("2010-01-10"));
        Assessment actualAssessment=assessmentMap.get(51);
        Assert.assertEquals(assessment,actualAssessment);
    }
}
