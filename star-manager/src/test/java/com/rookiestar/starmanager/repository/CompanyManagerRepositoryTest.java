package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.companymanager.CompanyManager;
import com.rookiestar.starmanager.util.DataBaseUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


/**
 * Description
 *
 * @author 曹向阳
 * @date 2021/7/13
 */
public class CompanyManagerRepositoryTest extends BaseTest {

    @Autowired
    private CompanyManagerRepository companyManagerRepository;

    private final Map<Integer, CompanyManager> companyManagerMap;

    public CompanyManagerRepositoryTest() throws Exception{
        companyManagerMap = DataBaseUtil.getInstance().getCompanyManagerMap();
    }

    @Test
    @Transactional
    public void findByCompanyIdAndEmailAndJobNumberTest() throws Exception{
        DataBaseUtil.getInstance().initCompanyManager(companyManagerRepository);

        CompanyManager companyManager = companyManagerRepository.findByCompanyIdAndEmailAndJobNumber(1,"2019302110260@whu.edu.cn",1521);

        CompanyManager expectCompanyManager = companyManagerMap.get(1521);

        Assert.assertEquals(expectCompanyManager,companyManager);
    }
}