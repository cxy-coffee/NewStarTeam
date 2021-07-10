package com.rookiestar.starmanager.util;

import com.rookiestar.starmanager.entity.*;
import com.rookiestar.starmanager.repository.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Util class that init database and get init data
 *
 * @author 曹向阳
 * @date 2021/7/9
 */
public class DataBaseUtil {
    private static DataBaseUtil instance;

    private final Map<Integer,Employee> employeeMap;
    private final Map<Integer,Experience> experienceMap;
    private final Map<Integer,Company> companyMap;
    private final Map<Integer, Department> departmentMap;
    private final Map<Integer, Position> positionMap;
    private final Map<Integer,Assessment> assessmentMap;

    private DataBaseUtil() throws Exception{
        employeeMap = initEmployeeMap();
        experienceMap = initExperienceMap();
        companyMap = initCompanyMap();
        departmentMap = initDepartmentMap();
        positionMap = initPositionMap();
        assessmentMap = initAssessmentMap();
    }

    public static DataBaseUtil getInstance() throws Exception{
        if(instance==null){
            instance = new DataBaseUtil();
        }
        return instance;
    }

    public void initCompany(CompanyRepository companyRepository) throws Exception{
        companyRepository.deleteAll();
        for(Map.Entry<Integer,Company> node:companyMap.entrySet()){
            companyRepository.save(node.getValue());
        }
    }
    public void initDepartment(DepartmentRepository departmentRepository) throws Exception{
        departmentRepository.deleteAll();
        for(Map.Entry<Integer,Department> node:departmentMap.entrySet()){
            departmentRepository.save(node.getValue());
        }
    }
    public void initPosition(PositionRepository positionRepository) throws Exception{
        positionRepository.deleteAll();
        for(Map.Entry<Integer,Position> node:positionMap.entrySet()){
            positionRepository.save(node.getValue());
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
    public void initAssessment(AssessmentRepository assessmentRepository) throws Exception{
        assessmentRepository.deleteAll();
        for(Map.Entry<Integer,Assessment> node:assessmentMap.entrySet()){
            assessmentRepository.save(node.getValue());
        }
    }

    private Map<Integer,Employee> initEmployeeMap() throws Exception{
        Map<Integer,Employee> map = new HashMap<>(10);
        map.put(5,new Employee("张三",DateUtil.parse("2000-01-10"),"男","2019302110260@whu.edu.cn","5",5,"123",null));
        map.put(6,new Employee("李四",DateUtil.parse("2001-01-11"),"女","2019302110261@whu.edu.cn","6",6,"456",null));
        map.put(7,new Employee("王五",DateUtil.parse("2002-01-12"),"男","2019302110262@whu.edu.cn","7",7,"123",null));
        map.put(8,new Employee("赵六",DateUtil.parse("2003-01-13"),"女","2019302110263@whu.edu.cn","8",8,"456",null));
        return map;
    }
    private Map<Integer,Experience> initExperienceMap() throws Exception{
        Map<Integer,Experience> map = new HashMap<>(20);
        map.put(5121,new Experience(5,1,2,1,1521,DateUtil.parse("2010-01-10"),null,false));
        map.put(5221,new Experience(5,2,2,1,2521,DateUtil.parse("2010-01-10"),null,true));
        map.put(6121,new Experience(6,1,2,1,1621,DateUtil.parse("2011-01-11"),null,false));
        map.put(6221,new Experience(6,2,2,1,2621,DateUtil.parse("2011-01-11"),null,true));
        map.put(7121,new Experience(7,1,2,1,1721,DateUtil.parse("2012-01-12"),null,true));
        map.put(7221,new Experience(7,2,2,1,2721,DateUtil.parse("2012-01-12"),null,false));
        map.put(8121,new Experience(8,1,2,1,1821,DateUtil.parse("2013-01-13"),null,true));
        map.put(8221,new Experience(8,2,2,1,2821,DateUtil.parse("2013-01-13"),null,false));
        return map;
    }
    private Map<Integer,Company> initCompanyMap(){
        Map<Integer,Company> map = new HashMap<>(5);
        map.put(1,new Company(1,"我的公司1",null,null));
        map.put(2,new Company(2,"我的公司2",null,null));
        return map;
    }
    private Map<Integer,Department> initDepartmentMap(){
        Map<Integer,Department> map = new HashMap<>(10);
        map.put(11,new Department(1,1,"公司1部门1",null));
        map.put(12,new Department(1,2,"公司1部门2",null));
        map.put(21,new Department(2,1,"公司2部门1",null));
        map.put(22,new Department(2,2,"公司2部门2",null));
        return map;
    }
    private Map<Integer,Position> initPositionMap(){
        Map<Integer,Position> map = new HashMap<>(20);
        map.put(111,new Position(1,1,1,"公司1部门1员工"));
        map.put(112,new Position(1,1,2,"公司1部门1经理"));
        map.put(121,new Position(1,2,1,"公司1部门2员工"));
        map.put(122,new Position(1,2,2,"公司1部门2经理"));
        map.put(211,new Position(2,1,1,"公司2部门1员工"));
        map.put(212,new Position(2,1,2,"公司2部门1经理"));
        map.put(221,new Position(2,2,1,"公司2部门2员工"));
        map.put(222,new Position(2,2,2,"公司2部门2经理"));
        return map;
    }
    private Map<Integer,Assessment> initAssessmentMap() throws Exception{
        Map<Integer,Assessment> map = new HashMap<>(20);
        map.put(51,new Assessment(5,1,DateUtil.parse("2010-01-10"),"0/10","51的表现"));
        map.put(52,new Assessment(5,2,DateUtil.parse("2010-01-10"),"0/10","52的表现"));
        map.put(61,new Assessment(6,1,DateUtil.parse("2011-01-11"),"0/10","61的表现"));
        map.put(62,new Assessment(6,2,DateUtil.parse("2011-01-11"),"0/10","62的表现"));
        map.put(71,new Assessment(7,1,DateUtil.parse("2012-01-12"),"0/10","71的表现"));
        map.put(72,new Assessment(7,2,DateUtil.parse("2012-01-12"),"0/10","72的表现"));
        map.put(81,new Assessment(8,1,DateUtil.parse("2013-01-13"),"0/10","81的表现"));
        map.put(82,new Assessment(8,2,DateUtil.parse("2013-01-13"),"0/10","82的表现"));
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
    public Map<Integer, Department> getDepartmentMap() {
        return departmentMap;
    }
    public Map<Integer, Position> getPositionMap() {
        return positionMap;
    }
    public Map<Integer, Assessment> getAssessmentMap() {
        return assessmentMap;
    }
}
