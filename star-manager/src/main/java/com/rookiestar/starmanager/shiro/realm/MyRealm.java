package com.rookiestar.starmanager.shiro.realm;

import com.rookiestar.starmanager.constant.UserTypes;
import com.rookiestar.starmanager.entity.companyManager.CompanyManager;
import com.rookiestar.starmanager.entity.employee.Employee;
import com.rookiestar.starmanager.constant.PermissionNames;
import com.rookiestar.starmanager.constant.RoleNames;
import com.rookiestar.starmanager.repository.CompanyManagerRepository;
import com.rookiestar.starmanager.repository.EmployeeRepository;
import com.rookiestar.starmanager.shiro.token.GenericToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Description
 *
 * @author 曹向阳
 * @date 2021/7/12
 */
public class MyRealm extends AuthorizingRealm {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyManagerRepository companyManagerRepository;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        GenericToken token = (GenericToken)principalCollection.getPrimaryPrincipal();

        String userType = token.getUserType();

        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        Set<String> roles=new HashSet<java.lang.String>();
        Set<String> permissions=new HashSet<java.lang.String>();

        if(UserTypes.EMPLOYEE.equals(userType)){
            roles.add(RoleNames.EMPLOYEE);
            permissions.add(PermissionNames.READ);
            permissions.add(PermissionNames.WRITE);
        }else if(UserTypes.COMPANY_MANAGER.equals(userType)){
            roles.add(RoleNames.COMPANY_MANAGER);
            permissions.add(PermissionNames.READ);
            permissions.add(PermissionNames.WRITE);
        }else if(UserTypes.MANAGER.equals(userType)){
            roles.add(RoleNames.MANAGER);
            permissions.add(PermissionNames.READ);
            permissions.add(PermissionNames.WRITE);
        }
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        GenericToken token = (GenericToken) authenticationToken;
        String userType = token.getUserType();
        String username = token.getUsername();
        String dbPassword = "";
        if(UserTypes.EMPLOYEE.equals(userType)){
            Employee employee = employeeRepository.findByAccountNumber(Integer.parseInt(username));
            if (employee == null) {
                throw new UnknownAccountException("用户名不存在");
            }
            dbPassword = employee.getPassword();
        }else if(UserTypes.COMPANY_MANAGER.equals(userType)){
            CompanyManager companyManager = token.getCompanyManager();
            CompanyManager dbCompanyManager = companyManagerRepository.findByCompanyIdAndEmailAndJobNumber(companyManager.getCompanyId(),companyManager.getEmail(),companyManager.getJobNumber());
            if(dbCompanyManager==null){
                throw new UnknownAccountException("用户名不存在");
            }
            dbPassword=dbCompanyManager.getPassword();
        }else if(UserTypes.MANAGER.equals(userType)){

        }
        return new SimpleAuthenticationInfo(token, dbPassword, getName());
    }
}
