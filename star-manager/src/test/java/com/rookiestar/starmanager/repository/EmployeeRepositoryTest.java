package com.rookiestar.starmanager.repository;

import com.rookiestar.starmanager.BaseTest;
import com.rookiestar.starmanager.entity.employee.Employee;
import com.rookiestar.starmanager.util.DataBaseUtil;
import com.rookiestar.starmanager.util.DataBaseUtilPages;
import com.rookiestar.starmanager.util.DateUtil;
import com.rookiestar.starmanager.util.PageUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Test class that test EmployeeRepository
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
public class EmployeeRepositoryTest extends BaseTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ExperienceRepository experienceRepository;

    private final Map<Integer,Employee> employeeMap;

    public EmployeeRepositoryTest() throws Exception{
        employeeMap = DataBaseUtil.getInstance().getEmployeeMap();
    }

    @Test
    @Transactional
    public void findAllEmployeesByCompanyTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);

        List<Employee> employees = employeeRepository.findAllEmployeesByCompany(1);
        for (Employee employee:employees) {
            employee.setBirthday(DateUtil.format(employee.getBirthday()));
        }

        List<Employee> actualEmployees = new ArrayList<>(employeeMap.values());

        Assert.assertEquals(employees,actualEmployees);
    }

    @Test
    @Transactional
    public void findPresentEmployeesByCompanyTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);

        List<Employee> employees = employeeRepository.findPresentEmployeesByCompany(1);
        for (Employee employee:employees) {
            employee.setBirthday(DateUtil.format(employee.getBirthday()));
        }

        List<Employee> actualEmployees = new ArrayList<>();
        actualEmployees.add(employeeMap.get(5));
        actualEmployees.add(employeeMap.get(6));

        Assert.assertEquals(employees,actualEmployees);
    }

    @Test
    @Transactional
    public void findPresentEmployeesByCompanyPageTest() throws Exception{
        DataBaseUtilPages.getInstance().initEmployee(employeeRepository);
        DataBaseUtilPages.getInstance().initExperience(experienceRepository);

        Assert.assertEquals("[Employee{name='员工10', birthday=Fri Mar 12 00:00:00 CST 2010, gender='女', email='员工10的email', identifyNumber='10', accountNumber=10, password='123', experiences=null}, Employee{name='员工11', birthday=Sat Mar 12 00:00:00 CST 2011, gender='男', email='员工11的email', identifyNumber='11', accountNumber=11, password='123', experiences=null}, Employee{name='员工12', birthday=Mon Mar 12 00:00:00 CST 2012, gender='女', email='员工12的email', identifyNumber='12', accountNumber=12, password='123', experiences=null}, Employee{name='员工13', birthday=Tue Mar 12 00:00:00 CST 2013, gender='男', email='员工13的email', identifyNumber='13', accountNumber=13, password='123', experiences=null}, Employee{name='员工14', birthday=Wed Mar 12 00:00:00 CST 2014, gender='女', email='员工14的email', identifyNumber='14', accountNumber=14, password='123', experiences=null}]",employeeRepository.findPresentEmployeesByCompany(3, PageUtil.getPageable(0,"accountNumber")).getContent().toString());
        Assert.assertEquals("[Employee{name='员工20', birthday=Thu Mar 12 00:00:00 CST 2020, gender='女', email='员工20的email', identifyNumber='20', accountNumber=20, password='123', experiences=null}, Employee{name='员工21', birthday=Fri Mar 12 00:00:00 CST 2021, gender='男', email='员工21的email', identifyNumber='21', accountNumber=21, password='123', experiences=null}, Employee{name='员工22', birthday=Sat Mar 12 00:00:00 CST 2022, gender='女', email='员工22的email', identifyNumber='22', accountNumber=22, password='123', experiences=null}, Employee{name='员工23', birthday=Sun Mar 12 00:00:00 CST 2023, gender='男', email='员工23的email', identifyNumber='23', accountNumber=23, password='123', experiences=null}, Employee{name='跳槽哥', birthday=Sat Jan 01 00:00:00 CST 2000, gender='男', email='2875233439@qq.com', identifyNumber='25', accountNumber=25, password='123', experiences=null}]",employeeRepository.findPresentEmployeesByCompany(3, PageUtil.getPageable(2,"accountNumber")).getContent().toString());
        Assert.assertEquals("[]",employeeRepository.findPresentEmployeesByCompany(3, PageUtil.getPageable(3,"accountNumber")).getContent().toString());
    }

    @Test
    @Transactional
    public void findMaxAccountNumberTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);

        int max = employeeRepository.findMaxAccountNumber();

        Assert.assertEquals(max,8);
    }

    @Test
    @Transactional
    public void findByAccountNumberTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);

        Employee employee = employeeRepository.findByAccountNumber(6);

        Employee actualEmployee = employeeMap.get(6);

        Assert.assertEquals(employee,actualEmployee);

    }


    @Test
    @Transactional
    public void findEmployeesByNameContainingTest() throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);

        List<Employee> employees=employeeRepository.findEmployeesByNameContaining("张");
        for (Employee employee:employees) {
            employee.setBirthday(DateUtil.format(employee.getBirthday()));
        }

        List<Employee> actualEmployees = new ArrayList<>();
        actualEmployees.add(employeeMap.get(5));

        Assert.assertEquals(employees,actualEmployees);
    }

    @Test
    @Transactional
    public void findByCompanyIdAndNamePageTest() throws Exception{
        DataBaseUtilPages.getInstance().initEmployee(employeeRepository);
        DataBaseUtilPages.getInstance().initExperience(experienceRepository);

        Assert.assertEquals("[Employee{name='员工20', birthday=Thu Mar 12 00:00:00 CST 2020, gender='女', email='员工20的email', identifyNumber='20', accountNumber=20, password='123', experiences=null}, Employee{name='员工21', birthday=Fri Mar 12 00:00:00 CST 2021, gender='男', email='员工21的email', identifyNumber='21', accountNumber=21, password='123', experiences=null}, Employee{name='员工22', birthday=Sat Mar 12 00:00:00 CST 2022, gender='女', email='员工22的email', identifyNumber='22', accountNumber=22, password='123', experiences=null}, Employee{name='员工23', birthday=Sun Mar 12 00:00:00 CST 2023, gender='男', email='员工23的email', identifyNumber='23', accountNumber=23, password='123', experiences=null}]",employeeRepository.findByCompanyIdAndName(3,"工",PageUtil.getPageable(2,"accountNumber")).getContent().toString());
        Assert.assertEquals("[]",employeeRepository.findByCompanyIdAndName(3,"工",PageUtil.getPageable(3,"accountNumber")).getContent().toString());
    }

    @Test
    @Transactional
    public void findEmployeeByIdentifyNumberTest()throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        Employee employee=employeeRepository.findByIdentifyNumber("5");

        employee.setBirthday(DateUtil.format(employee.getBirthday()));

        Employee actualEmployee=employeeMap.get(5);

        Assert.assertEquals(employee,actualEmployee);
    }

    @Test
    @Transactional
    public void findEmployeesByGenderTest()throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        DataBaseUtil.getInstance().initExperience(experienceRepository);
        List<Employee> employees=employeeRepository.findEmployeesByGender("男");
        for (Employee employee:employees) {
            employee.setBirthday(DateUtil.format(employee.getBirthday()));
        }
        List<Employee> actualEmployees=new ArrayList<>();
        actualEmployees.add(employeeMap.get(5));
        actualEmployees.add(employeeMap.get(7));

        Assert.assertEquals(employees,actualEmployees);
    }

    @Test
    @Transactional
    public void findByCompanyIdAndGenderPageTest() throws Exception{
        DataBaseUtilPages.getInstance().initEmployee(employeeRepository);
        DataBaseUtilPages.getInstance().initExperience(experienceRepository);

        Assert.assertEquals("[Employee{name='员工21', birthday=Fri Mar 12 00:00:00 CST 2021, gender='男', email='员工21的email', identifyNumber='21', accountNumber=21, password='123', experiences=null}, Employee{name='员工23', birthday=Sun Mar 12 00:00:00 CST 2023, gender='男', email='员工23的email', identifyNumber='23', accountNumber=23, password='123', experiences=null}, Employee{name='跳槽哥', birthday=Sat Jan 01 00:00:00 CST 2000, gender='男', email='2875233439@qq.com', identifyNumber='25', accountNumber=25, password='123', experiences=null}]",employeeRepository.findByCompanyIdAndGender(3,"男",PageUtil.getPageable(1,"accountNumber")).getContent().toString());
        Assert.assertEquals("[]",employeeRepository.findByCompanyIdAndGender(3,"男",PageUtil.getPageable(2,"accountNumber")).getContent().toString());
    }

    @Test
    @Transactional
    public void findByEmailTest()throws Exception{
        DataBaseUtil.getInstance().initEmployee(employeeRepository);
        Employee employee=employeeRepository.findByEmail("2019302110260@whu.edu.cn");
        Employee actualEmployee=employeeMap.get(5);
        Assert.assertEquals(employee,actualEmployee);
    }


}