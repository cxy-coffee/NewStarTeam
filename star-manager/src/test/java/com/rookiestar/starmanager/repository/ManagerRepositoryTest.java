package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.manager.Manager;
import com.rookiestar.starmanager.util.DataBaseUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Description
 *
 * @author 曹向阳
 * @date 2021/7/14
 */
public class ManagerRepositoryTest extends BaseTest {
    @Autowired
    private ManagerRepository managerRepository;

    private final Map<Integer, Manager> managerMap;

    public ManagerRepositoryTest() throws Exception{
        managerMap = DataBaseUtil.getInstance().getManagerMap();
    }

    @Test
    @Transactional
    public void findByAccountNumberTest() throws Exception{
        DataBaseUtil.getInstance().initManager(managerRepository);

        Manager actualManager = managerRepository.findByAccountNumber(666);

        Manager expectManager = managerMap.get(666);

        Assert.assertEquals(expectManager,actualManager);
    }
}