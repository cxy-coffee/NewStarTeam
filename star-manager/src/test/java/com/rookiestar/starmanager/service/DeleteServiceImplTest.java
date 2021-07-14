package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.company.CompanyToReview;
import com.rookiestar.starmanager.entity.employee.Employee;
import com.rookiestar.starmanager.repository.CompanyToReviewRepository;
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
 * @time 17:11
 */
public class DeleteServiceImplTest extends BaseTest {
    @Autowired
    private DeleteService deleteService;
    @Autowired
    private CompanyToReviewRepository companyToReviewRepository;
    private final Map<Integer, CompanyToReview> companyToReviewMap;

    public DeleteServiceImplTest() throws Exception {
        companyToReviewMap=DataBaseUtil.getInstance().getCompanyToReviewMap();
    }

    @Test
    @Transactional
    public void deleteCompanyToReviewByCompanyIdTest()throws Exception{
        DataBaseUtil.getInstance().initCompanyToReview(companyToReviewRepository);

        deleteService.deleteCompanyToReviewByCompanyId(1);
        List<CompanyToReview> companyToReviewList=companyToReviewRepository.findAll();
        List<CompanyToReview> actualCompanyToReviewList=new ArrayList<>();
        actualCompanyToReviewList.add(new CompanyToReview(2,"我的公司2","Bob","2019302110243@whu.edu.cn","四川省成都市锦江区","88555573"));
        Assert.assertEquals(companyToReviewList,actualCompanyToReviewList);

    }
}
