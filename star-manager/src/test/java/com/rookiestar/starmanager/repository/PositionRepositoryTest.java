package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.position.Position;
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
 * @date 2021/7/14
 * @time 16:09
 */
public class PositionRepositoryTest extends BaseTest {
    @Autowired
    private PositionRepository positionRepository;
    private final Map<Integer, Position> positionMap;
    public PositionRepositoryTest() throws Exception{
        positionMap= DataBaseUtil.getInstance().getPositionMap();
    }

    @Test
    @Transactional
    public void findByCompanyIdAndDepartmentIdAndPositionIdTest()throws Exception{
        DataBaseUtil.getInstance().initPosition(positionRepository);
        Position position=positionRepository.findByCompanyIdAndDepartmentIdAndPositionId(1,1,1);
        Position actualPosition=positionMap.get(111);
        Assert.assertEquals(position,actualPosition);
    }

    @Test
    @Transactional
    public void deletePositionByCompanyIdAndDepartmentIdAndPositionIdTest()throws Exception{
        DataBaseUtil.getInstance().initPosition(positionRepository);
        Position position=positionRepository.findByCompanyIdAndDepartmentIdAndPositionId(1,1,1);
        Assert.assertNotEquals(position,null);
        positionRepository.deletePositionByCompanyIdAndDepartmentIdAndPositionId(1,1,1);
        position=positionRepository.findByCompanyIdAndDepartmentIdAndPositionId(1,1,1);
        Assert.assertNull(position);
    }

    @Test
    @Transactional
    public void findByCompanyIdAndDepartmentIdTest()throws Exception{
        DataBaseUtil.getInstance().initPosition(positionRepository);
        List<Position> positionList=new ArrayList<>();
        Position position=new Position(1,1,1,"公司1部门1员工");
        Position position1=new Position(1,1,2,"公司1部门1经理");
        positionList.add(position);
        positionList.add(position1);
        Assert.assertEquals(positionList,positionRepository.findByCompanyIdAndDepartmentId(1,1));
    }
}
