package com.rookiestar.starmanager.util;

import com.rookiestar.starmanager.entity.Company;
import com.rookiestar.starmanager.entity.Employee;
import com.rookiestar.starmanager.entity.Experience;
import com.rookiestar.starmanager.repository.CompanyRepository;
import com.rookiestar.starmanager.repository.EmployeeRepository;
import com.rookiestar.starmanager.repository.ExperienceRepository;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class DataBaseUtil {
    private static DataBaseUtil instance;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final Map<Integer,Employee> employeeMap;
    private final Map<Integer,Experience> experienceMap;
    private final Map<Integer,Company> companyMap;

    private DataBaseUtil() throws Exception{
        employeeMap = initEmployeeMap();
        experienceMap = initExperienceMap();
        companyMap = initCompanyMap();
    }

    public static DataBaseUtil getInstance() throws Exception{
        if(instance==null){
            instance = new DataBaseUtil();
        }
        return instance;
    }

    //初始化数据库
    public void initCompany(CompanyRepository companyRepository) throws Exception{
        companyRepository.deleteAll();
        for(Map.Entry<Integer,Company> node:companyMap.entrySet()){
            companyRepository.save(node.getValue());
        }
    }
    public void initEmployee(EmployeeRepository employeeRepository) throws Exception{
        employeeRepository.deleteAll();
        for(Map.Entry<Integer,Employee> node:employeeMap.entrySet()){
            employeeRepository.save(node.getValue());
        }
    }
    public void initExperience(ExperienceRepository experienceRepository) throws Exception{
        experienceRepository.deleteAll();
        for(Map.Entry<Integer,Experience> node:experienceMap.entrySet()){
            experienceRepository.save(node.getValue());
        }
    }

    //初始化数据成员map
    private Map<Integer,Employee> initEmployeeMap() throws Exception{
        Map<Integer,Employee> map = new HashMap<>();
        map.put(5,new Employee("张三",sdf.parse("2000-01-10"),"男","1025405845@qq.com",5,"123",null));
        map.put(6,new Employee("李四",sdf.parse("2001-01-11"),"女","李四邮箱",6,"456",null));
        map.put(7,new Employee("王五",sdf.parse("2002-01-12"),"男","王五邮箱",7,"123",null));
        map.put(8,new Employee("赵六",sdf.parse("2003-01-13"),"女","赵六邮箱",8,"456",null));
        return map;
    }
    private Map<Integer,Experience> initExperienceMap() throws Exception{
        Map<Integer,Experience> map = new HashMap<>();
        map.put(15,new Experience(5,1,15,sdf.parse("2010-01-10"),null,false));
        map.put(25,new Experience(5,2,25,sdf.parse("2010-01-10"),null,true));
        map.put(16,new Experience(6,1,16,sdf.parse("2011-01-11"),null,false));
        map.put(26,new Experience(6,2,26,sdf.parse("2011-01-11"),null,true));
        map.put(17,new Experience(7,1,17,sdf.parse("2012-01-12"),null,true));
        map.put(27,new Experience(7,2,27,sdf.parse("2012-01-12"),null,false));
        map.put(18,new Experience(8,1,18,sdf.parse("2013-01-13"),null,true));
        map.put(28,new Experience(8,2,28,sdf.parse("2013-01-13"),null,false));
        return map;
    }
    private Map<Integer,Company> initCompanyMap(){
        Map<Integer,Company> map = new HashMap<>();
        map.put(1,new Company(1,"我的公司1",null));
        map.put(2,new Company(2,"我的公司2",null));
        return map;
    }

    public Map<Integer, Employee> getEmployeeMap() {
        return employeeMap;
    }
    public Map<Integer, Experience> getExperienceMap() {
        return experienceMap;
    }
    public Map<Integer, Company> getCompanyMap() {
        return companyMap;
    }
}
