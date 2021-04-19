package com.zhenhao;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by zhenhao on 2021/4/16 9:07
 * Content:  创建securityManager-》主体提交认证-》securityManager认证-》Authenticator认证 -》 realm认证
 *           创建securityManager-》主体提交授权-》securityManager授权-》Authorizer授权 -》 realm获取角色权限数据
 */
public class ShiroDemoTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
    @Before
    public void addAccount(){
        simpleAccountRealm.addAccount("zhenhao","zhenhao","admin","user");
    }
    @Test
    public void testAuthentication(){
        //1、构建securityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);
        //2、主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhenhao", "zhenhao");
        subject.login(token);

        //输出是否认证成功
        System.out.println(subject.isAuthenticated());

//        subject.logout();
//        System.out.println(subject.isAuthenticated());
//
//

        subject.checkRoles("admin","user1");
        System.out.println(subject.getPrincipal());
    }

}
