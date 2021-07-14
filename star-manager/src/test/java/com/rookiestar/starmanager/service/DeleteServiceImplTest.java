package com.rookiestar.starmanager.service;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.company.CompanyToReview;
import com.rookiestar.starmanager.entity.department.Department;
import com.rookiestar.starmanager.entity.position.Position;
import com.rookiestar.starmanager.repository.CompanyToReviewRepository;
import com.rookiestar.starmanager.repository.DepartmentRepository;
import com.rookiestar.starmanager.repository.PositionRepository;
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
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
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

    @Test
    @Transactional
    public void deletePositionByCompanyIdAndDepartmentIdAndPositionIdTest()throws Exception{
        DataBaseUtil.getInstance().initPosition(positionRepository);
        deleteService.deletePositionByCompanyIdAndDepartmentIdAndPositionId(1,1,1);
        List<Position> positionList=positionRepository.findAll();
        List<Position> actualPositionList=new ArrayList<>();
        actualPositionList.add(new Position(1,1,2,"公司1部门1经理"));
        actualPositionList.add(new Position(1,2,1,"公司1部门2员工"));
        actualPositionList.add(new Position(1,2,2,"公司1部门2经理"));
        actualPositionList.add(new Position(2,1,1,"公司2部门1员工"));
        actualPositionList.add(new Position(2,1,2,"公司2部门1经理"));
        actualPositionList.add(new Position(2,2,1,"公司2部门2员工"));
        actualPositionList.add(new Position(2,2,2,"公司2部门2经理"));
        Assert.assertEquals(positionList,actualPositionList);
    }

    @Test
    @Transactional
    public void deleteDepartmentByCompanyIdAndDepartmentIDTest()throws Exception{
        DataBaseUtil.getInstance().initDepartment(departmentRepository);
        deleteService.deleteDepartmentByCompanyIdAndDepartmentId(1,1);
        List<Department> departmentList=departmentRepository.findAll();
        List<Department> actualDepartmentList=new ArrayList<>();
        actualDepartmentList.add(new Department(1,2,"公司1部门2",null));
        actualDepartmentList.add(new Department(2,1,"公司2部门1",null));
        actualDepartmentList.add(new Department(2,2,"公司2部门2",null));
        Assert.assertEquals(departmentList,actualDepartmentList);

    }
}
