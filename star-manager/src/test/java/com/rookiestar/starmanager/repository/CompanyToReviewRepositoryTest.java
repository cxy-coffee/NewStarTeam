package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.company.CompanyToReview;
import com.rookiestar.starmanager.util.DataBaseUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author chris
 * @date 2021/7/13
 * @time 16:51
 */
public class CompanyToReviewRepositoryTest extends BaseTest {
    @Autowired
    CompanyToReviewRepository companyToReviewRepository;

    private final Map<Integer, CompanyToReview> companyToReviewMap;

    public CompanyToReviewRepositoryTest()throws Exception{
        companyToReviewMap= DataBaseUtil.getInstance().getCompanyToReviewMap();
    }

    @Test
    @Transactional
    public void findAllTest()throws Exception{
        DataBaseUtil.getInstance().initCompanyToReview(companyToReviewRepository);
        List<CompanyToReview> companyToReviewList=companyToReviewRepository.findAll();
        List<CompanyToReview> actualCompanyToReviewList=new ArrayList<>(companyToReviewMap.values());
        Assert.assertEquals(companyToReviewList,actualCompanyToReviewList);
    }
    @Test
    @Transactional
    public void deleteCompanyToReviewByCompanyIdTest()throws Exception{
        DataBaseUtil.getInstance().initCompanyToReview(companyToReviewRepository);
        companyToReviewRepository.deleteCompanyToReviewByCompanyId(1);
        List<CompanyToReview> companyToReviewList=companyToReviewRepository.findAll();
        List<CompanyToReview> actualCompanyToReviewList=new ArrayList<>();
        actualCompanyToReviewList.add(companyToReviewMap.get(2));
        Assert.assertEquals(companyToReviewList,actualCompanyToReviewList);
    }

}
