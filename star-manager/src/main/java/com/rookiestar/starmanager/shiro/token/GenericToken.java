package com.rookiestar.starmanager.shiro.token;

import com.rookiestar.starmanager.entity.companyManager.CompanyManager;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * GenericToken
 *
 * @author 曹向阳
 * @date 2021/7/13
 */
public class GenericToken extends UsernamePasswordToken {
    private CompanyManager companyManager;
    private String userType;

    public GenericToken() {
    }

    public GenericToken(String username, char[] password) {
        super(username, password);
    }

    public GenericToken(String username, String password) {
        super(username, password);
    }

    public GenericToken(String username, char[] password, String host) {
        super(username, password, host);
    }

    public GenericToken(String username, String password, String host) {
        super(username, password, host);
    }

    public GenericToken(String username, char[] password, boolean rememberMe) {
        super(username, password, rememberMe);
    }

    public GenericToken(String username, String password, boolean rememberMe) {
        super(username, password, rememberMe);
    }

    public GenericToken(String username, char[] password, boolean rememberMe, String host) {
        super(username, password, rememberMe, host);
    }

    public GenericToken(String username, String password, boolean rememberMe, String host) {
        super(username, password, rememberMe, host);
    }

    public CompanyManager getCompanyManager() {
        return companyManager;
    }

    public void setCompanyManager(CompanyManager companyManager) {
        this.companyManager = companyManager;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public void clear() {
        super.clear();
        this.companyManager=null;
        this.userType=null;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
