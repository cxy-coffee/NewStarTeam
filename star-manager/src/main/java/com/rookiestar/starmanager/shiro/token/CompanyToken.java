package com.rookiestar.starmanager.shiro.token;

import com.rookiestar.starmanager.entity.companyManager.CompanyManager;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * CompanyToken
 *
 * @author 曹向阳
 * @date 2021/7/13
 */
public class CompanyToken extends UsernamePasswordToken {
    private CompanyManager companyManager;

    public CompanyToken() {
    }

    public CompanyToken(String username, char[] password) {
        super(username, password);
    }

    public CompanyToken(String username, String password) {
        super(username, password);
    }

    public CompanyToken(String username, char[] password, String host) {
        super(username, password, host);
    }

    public CompanyToken(String username, String password, String host) {
        super(username, password, host);
    }

    public CompanyToken(String username, char[] password, boolean rememberMe) {
        super(username, password, rememberMe);
    }

    public CompanyToken(String username, String password, boolean rememberMe) {
        super(username, password, rememberMe);
    }

    public CompanyToken(String username, char[] password, boolean rememberMe, String host) {
        super(username, password, rememberMe, host);
    }

    public CompanyToken(String username, String password, boolean rememberMe, String host) {
        super(username, password, rememberMe, host);
    }

    public CompanyManager getCompanyManager() {
        return companyManager;
    }

    public void setCompanyManager(CompanyManager companyManager) {
        this.companyManager = companyManager;
    }

    @Override
    public void clear() {
        super.clear();
        this.companyManager=null;
    }

    @Override
    public String toString() {
        return super.toString()+companyManager.toString();
    }
}
