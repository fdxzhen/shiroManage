package com.zhenhao.shiro;

import junit.framework.TestCase;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Created by zhenhao on 2021/4/16 9:34
 * Content:
 */
public class CustomRealmTest  {


    @Test
    public void testAuthentication(){

        CustomRealm customRealm = new CustomRealm();
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(customRealm);

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("root","root" );
        subject.login(token);

        subject.checkRole("admin");
        subject.checkPermission("user:add");


        System.out.println(subject.isAuthenticated());
    }
}
