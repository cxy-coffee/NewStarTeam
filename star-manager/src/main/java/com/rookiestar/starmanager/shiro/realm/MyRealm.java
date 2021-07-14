package com.rookiestar.starmanager.shiro.realm;

import com.rookiestar.starmanager.entity.companymanager.CompanyManager;
import com.rookiestar.starmanager.entity.employee.Employee;
import com.rookiestar.starmanager.repository.CompanyManagerRepository;
import com.rookiestar.starmanager.repository.EmployeeRepository;
import com.rookiestar.starmanager.shiro.token.CompanyToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * Description
 *
 * @author 曹向阳
 * @date 2021/7/12
 */
public class MyRealm extends AuthorizingRealm {

    @Value("${userType.employeePre}")
    private String employeePre;
    @Value("${userType.companyPre}")
    private String companyPre;
    @Value("${userType.managerPre}")
    private String managerPre;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyManagerRepository companyManagerRepository;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
        protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String preUsername = usernamePasswordToken.getUsername();
        String userType = preUsername.substring(0, employeePre.length());
        String username = preUsername.substring(employeePre.length());
        String dbPassword = "";
        if(employeePre.equals(userType)){
            Employee employee = employeeRepository.findByAccountNumber(Integer.parseInt(username));
            if (employee == null) {
                throw new UnknownAccountException("用户名不存在");
            }
            dbPassword = employee.getPassword();
        }else if(companyPre.equals(userType)){
            CompanyToken companyToken = (CompanyToken)usernamePasswordToken;
            CompanyManager companyManager = companyToken.getCompanyManager();
            CompanyManager dbCompanyManager = companyManagerRepository.findByCompanyIdAndEmailAndJobNumber(companyManager.getCompanyId(),companyManager.getEmail(),companyManager.getJobNumber());
            if(dbCompanyManager==null){
                throw new UnknownAccountException("用户名不存在");
            }
            dbPassword=dbCompanyManager.getPassword();
        }else if(managerPre.equals(userType)){

        }
        return new SimpleAuthenticationInfo(username, dbPassword, getName());
    }
}
