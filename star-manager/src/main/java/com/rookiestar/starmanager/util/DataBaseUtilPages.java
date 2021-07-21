package com.rookiestar.starmanager.util;

import com.rookiestar.starmanager.entity.assessment.Assessment;
import com.rookiestar.starmanager.entity.company.Company;
import com.rookiestar.starmanager.entity.company.CompanyToReview;
import com.rookiestar.starmanager.entity.companymanager.CompanyManager;
import com.rookiestar.starmanager.entity.department.Department;
import com.rookiestar.starmanager.entity.employee.Employee;
import com.rookiestar.starmanager.entity.experience.Experience;
import com.rookiestar.starmanager.entity.manager.Manager;
import com.rookiestar.starmanager.entity.position.Position;
import com.rookiestar.starmanager.repository.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Description
 *
 * @author 曹向阳
 * @date 2021/7/20
 */
public class DataBaseUtilPages {
    private static DataBaseUtilPages instance;

    private final Map<Integer, Employee> employeeMap;
    private final Map<Integer, Experience> experienceMap;
    private final Map<Integer, Company> companyMap;
    private final Map<Integer, Department> departmentMap;
    private final Map<Integer, Position> positionMap;
    private final Map<Integer, Assessment> assessmentMap;
    private final Map<Integer, CompanyManager> companyManagerMap;
    private final Map<Integer, CompanyToReview> companyToReviewMap;
    private final Map<Integer, Manager> managerMap;

    public static DataBaseUtilPages getInstance() throws Exception{
        if(instance==null){
            instance = new DataBaseUtilPages();
        }
        return instance;
    }

    private DataBaseUtilPages() throws Exception{
        employeeMap = initEmployeeMap();
        experienceMap = initExperienceMap();
        companyMap = initCompanyMap();
        departmentMap = initDepartmentMap();
        positionMap = initPositionMap();
        assessmentMap = initAssessmentMap();
        companyManagerMap = initCompanyManagerMap();
        companyToReviewMap=initCompanyToReviewMap();
        managerMap = initManagerMap();
    }

    public void initCompany(CompanyRepository companyRepository){
        companyRepository.deleteAll();
        for(Map.Entry<Integer,Company> node:companyMap.entrySet()){
            companyRepository.save(node.getValue());
        }
    }
    public void initDepartment(DepartmentRepository departmentRepository){
        departmentRepository.deleteAll();
        for(Map.Entry<Integer,Department> node:departmentMap.entrySet()){
            departmentRepository.save(node.getValue());
        }
    }
    public void initPosition(PositionRepository positionRepository){
        positionRepository.deleteAll();
        for(Map.Entry<Integer,Position> node:positionMap.entrySet()){
            positionRepository.save(node.getValue());
        }
    }
    public void initEmployee(EmployeeRepository employeeRepository){
        employeeRepository.deleteAll();
        for(Map.Entry<Integer,Employee> node:employeeMap.entrySet()){
            employeeRepository.save(node.getValue());
        }
    }
    public void initExperience(ExperienceRepository experienceRepository){
        experienceRepository.deleteAll();
        for(Map.Entry<Integer,Experience> node:experienceMap.entrySet()){
            experienceRepository.save(node.getValue());
        }
    }
    public void initAssessment(AssessmentRepository assessmentRepository){
        assessmentRepository.deleteAll();
        for(Map.Entry<Integer,Assessment> node:assessmentMap.entrySet()){
            assessmentRepository.save(node.getValue());
        }
    }
    public void initCompanyToReview(CompanyToReviewRepository companyToReviewRepository){
        companyToReviewRepository.deleteAll();
        for(Map.Entry<Integer,CompanyToReview> node:companyToReviewMap.entrySet()){
            companyToReviewRepository.save(node.getValue());
        }
    }
    public void initCompanyManager(CompanyManagerRepository companyManagerRepository){
        companyManagerRepository.deleteAll();
        for(Map.Entry<Integer,CompanyManager> node:companyManagerMap.entrySet()){
            companyManagerRepository.save(node.getValue());
        }
    }
    public void initManager(ManagerRepository managerRepository) throws Exception{
        managerRepository.deleteAll();
        for(Map.Entry<Integer,Manager> node:managerMap.entrySet()){
            managerRepository.save(node.getValue());
        }
    }

    private Map<Integer,Employee> initEmployeeMap() throws Exception{
        Map<Integer,Employee> map = new HashMap<>(30);
        map.put(5,new Employee("张三",DateUtil.parse("2000-01-10"),"男","2019302110260@whu.edu.cn","5",5,"123",null));
        map.put(6,new Employee("李四",DateUtil.parse("2001-01-11"),"女","2019302110261@whu.edu.cn","6",6,"456",null));
        map.put(7,new Employee("王五",DateUtil.parse("2002-01-12"),"男","2019302110262@whu.edu.cn","7",7,"123",null));
        map.put(8,new Employee("赵六",DateUtil.parse("2003-01-13"),"女","2019302110263@whu.edu.cn","8",8,"456",null));

        for (int i = 10; i < 24; i++) {
            map.put(i,new Employee("员工"+i,DateUtil.parse("20"+i+"-03-12"),i%2==0?"女":"男","员工"+i+"的email",String.valueOf(i),i,"123",null));
        }

        map.put(25,new Employee("跳槽哥",DateUtil.parse("2000-01-01"),"男","2875233439@qq.com","25",25,"123",null));
        return map;
    }
    private Map<Integer,Experience> initExperienceMap() throws Exception{
        Map<Integer,Experience> map = new HashMap<>(50);
        map.put(5121,new Experience(5,1,2,1,1521,DateUtil.parse("2010-01-10"),null,false));
        map.put(5221,new Experience(5,2,2,1,2521,DateUtil.parse("2010-01-10"),null,true));
        map.put(6121,new Experience(6,1,2,1,1621,DateUtil.parse("2011-01-11"),null,false));
        map.put(6221,new Experience(6,2,2,1,2621,DateUtil.parse("2011-01-11"),null,true));
        map.put(7121,new Experience(7,1,2,1,1721,DateUtil.parse("2012-01-12"),null,true));
        map.put(7221,new Experience(7,2,2,1,2721,DateUtil.parse("2012-01-12"),null,false));
        map.put(8121,new Experience(8,1,2,1,1821,DateUtil.parse("2013-01-13"),null,true));
        map.put(8221,new Experience(8,2,2,1,2821,DateUtil.parse("2013-01-13"),null,false));

        for (int i = 10; i < 24; i++) {
            map.put(Integer.parseInt(i+"311"),new Experience(i,3,1,1,Integer.parseInt("3"+i+"11"),DateUtil.parse("2077-03-12"),null,false));
        }

        for (int i = 4; i < 17; i++) {
            map.put(Integer.parseInt("25"+i+"21"),new Experience(25,i,2,1,Integer.parseInt(i+"2521"),DateUtil.parse("2077-03-12"),null,true));
        }

        map.put(25321,new Experience(25,3,2,1,32521,DateUtil.parse("2077-03-12"),null,false));
        return map;
    }
    private Map<Integer,Company> initCompanyMap(){
        Map<Integer,Company> map = new HashMap<>(5);
        map.put(1,new Company(1,"我的公司1","Alan","lihaoc@whu.edu.cn","湖北省武汉市洪山区","88555273",null,null));
        map.put(2,new Company(2,"我的公司2","Bob","2019302110243@whu.edu.cn","四川省成都市锦江区","88555573",null,null));
        map.put(3,new Company(3,"我的公司3","Bob","2077302110999@whu.edu.cn","四川省成都市锦江区","88555573",null,null));
        return map;
    }
    private Map<Integer, CompanyToReview> initCompanyToReviewMap(){
        Map<Integer,CompanyToReview> map = new HashMap<>(5);
        map.put(1,new CompanyToReview(1,"我的公司1","Alan","lihaoc@whu.edu.cn","湖北省武汉市洪山区","88555273"));
        map.put(2,new CompanyToReview(2,"我的公司2","Bob","2019302110243@whu.edu.cn","四川省成都市锦江区","88555573"));
        map.put(3,new CompanyToReview(3,"我的公司3","Bob","2019302110243@whu.edu.cn","四川省成都市锦江区","88555573"));
        map.put(4,new CompanyToReview(4,"我的公司4","Bob","2019302110243@whu.edu.cn","四川省成都市锦江区","88555573"));
        map.put(5,new CompanyToReview(5,"我的公司5","Bob","2019302110243@whu.edu.cn","四川省成都市锦江区","88555573"));
        map.put(6,new CompanyToReview(6,"我的公司6","Bob","2019302110243@whu.edu.cn","四川省成都市锦江区","88555573"));
        map.put(7,new CompanyToReview(7,"我的公司7","Bob","2019302110243@whu.edu.cn","四川省成都市锦江区","88555573"));
        map.put(8,new CompanyToReview(8,"我的公司8","Bob","2019302110243@whu.edu.cn","四川省成都市锦江区","88555573"));
        return map;
    }
    private Map<Integer,Department> initDepartmentMap(){
        Map<Integer,Department> map = new HashMap<>(10);
        map.put(11,new Department(1,1,"公司1部门1",null));
        map.put(12,new Department(1,2,"公司1部门2",null));
        map.put(21,new Department(2,1,"公司2部门1",null));
        map.put(22,new Department(2,2,"公司2部门2",null));

        map.put(31,new Department(3,1,"公司3部门1",null));
        map.put(32,new Department(3,2,"公司3部门2",null));
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

        map.put(311,new Position(3,1,1,"公司3部门1员工"));
        map.put(312,new Position(3,1,2,"公司3部门1经理"));
        map.put(321,new Position(3,2,1,"公司3部门2员工"));
        map.put(322,new Position(3,2,2,"公司3部门2经理"));
        return map;
    }
    private Map<Integer,Assessment> initAssessmentMap() throws Exception{
        Map<Integer,Assessment> map = new HashMap<>(50);
        map.put(51,new Assessment(5,1,DateUtil.parse("2010-01-10"),"0/10","51的表现"));
        map.put(52,new Assessment(5,2,DateUtil.parse("2010-01-10"),"0/10","52的表现"));
        map.put(61,new Assessment(6,1,DateUtil.parse("2011-01-11"),"0/10","61的表现"));
        map.put(62,new Assessment(6,2,DateUtil.parse("2011-01-11"),"0/10","62的表现"));
        map.put(71,new Assessment(7,1,DateUtil.parse("2012-01-12"),"0/10","71的表现"));
        map.put(72,new Assessment(7,2,DateUtil.parse("2012-01-12"),"0/10","72的表现"));
        map.put(81,new Assessment(8,1,DateUtil.parse("2013-01-13"),"0/10","81的表现"));
        map.put(82,new Assessment(8,2,DateUtil.parse("2013-01-13"),"0/10","82的表现"));

        for (int i = 10; i < 24; i++) {
            map.put(Integer.parseInt(i+"3"),new Assessment(i,3,DateUtil.parse("2077-03-12"),"0/10",i+"3的表现"));
        }

        for (int i = 3; i < 17; i++) {
            map.put(Integer.parseInt("25"+i),new Assessment(25,i,DateUtil.parse("2077-03-12"),"0/10","25"+i+"的表现"));
        }
        return map;
    }
    private Map<Integer,CompanyManager> initCompanyManagerMap(){
        Map<Integer,CompanyManager> map = new HashMap<>(10);
        map.put(1521,new CompanyManager(1,"2019302110260@whu.edu.cn",1521,"234"));
        map.put(2721,new CompanyManager(2,"2019302110260@whu.edu.cn",2721,"345"));

        map.put(32521,new CompanyManager(3,"2019302110260@whu.edu.cn",32521,"234"));
        return map;
    }
    private Map<Integer,Manager> initManagerMap() throws Exception{
        Map<Integer,Manager> map = new HashMap<>(10);
        map.put(666,new Manager("超管一号","86199",DateUtil.parse("2077-01-01"),"2019302110260@whu.edu.cn","男",666,"888"));
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
    public Map<Integer, CompanyManager> getCompanyManagerMap() {
        return companyManagerMap;
    }
    public Map<Integer, CompanyToReview> getCompanyToReviewMap() {
        return companyToReviewMap;
    }
    public Map<Integer, Manager> getManagerMap() {
        return managerMap;
    }
}
