package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.assessment.Assessment;
import com.rookiestar.starmanager.entity.department.Department;
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
public class DepartmentRepositoryTest extends BaseTest {
    @Autowired
    DepartmentRepository departmentRepository;
    private final Map<Integer, Department> departmentMap;
    public DepartmentRepositoryTest() throws Exception {
        departmentMap= DataBaseUtil.getInstance().getDepartmentMap();
    }

    @Test
    @Transactional
    public void findByCompanyIdAndDepartmentIdTest()throws Exception{
        DataBaseUtil.getInstance().initDepartment(departmentRepository);
        Department department=departmentRepository.findByCompanyIdAndDepartmentId(1,1);
        Department actualDepartment=departmentMap.get(11);
        Assert.assertEquals(department,actualDepartment);
    }
    @Test
    @Transactional
    public void deleteDepartmentByCompanyIdAndDepartmentIdTest()throws Exception{
        DataBaseUtil.getInstance().initDepartment(departmentRepository);
        Department department=departmentRepository.findByCompanyIdAndDepartmentId(1,1);
        Assert.assertNotEquals(department,null);
        departmentRepository.deleteDepartmentByCompanyIdAndDepartmentId(1,1);
        department=departmentRepository.findByCompanyIdAndDepartmentId(1,1);
        Assert.assertNull(department);
    }

    @Test
    @Transactional
    public void findByCompanyIdTest()throws Exception{
        DataBaseUtil.getInstance().initDepartment(departmentRepository);
        List<Department> departmentList=new ArrayList<>();
        departmentList.add(new Department(1,1,"部门1",null));
        departmentList.add(new Department(1,2,"部门2",null));
        Assert.assertEquals(departmentRepository.findByCompanyId(1),departmentList);
    }
}
