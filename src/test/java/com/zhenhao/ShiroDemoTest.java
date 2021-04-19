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
 * Content:  ����securityManager-�������ύ��֤-��securityManager��֤-��Authenticator��֤ -�� realm��֤
 *           ����securityManager-�������ύ��Ȩ-��securityManager��Ȩ-��Authorizer��Ȩ -�� realm��ȡ��ɫȨ������
 */
public class ShiroDemoTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
    @Before
    public void addAccount(){
        simpleAccountRealm.addAccount("zhenhao","zhenhao","admin","user");
    }
    @Test
    public void testAuthentication(){
        //1������securityManager����
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);
        //2�������ύ��֤����
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhenhao", "zhenhao");
        subject.login(token);

        //����Ƿ���֤�ɹ�
        System.out.println(subject.isAuthenticated());

//        subject.logout();
//        System.out.println(subject.isAuthenticated());
//
//

        subject.checkRoles("admin","user1");
        System.out.println(subject.getPrincipal());
    }

}
