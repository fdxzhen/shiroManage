package com.zhenhao.shiro;

import com.zhenhao.bean.Permission;
import com.zhenhao.bean.Role;
import com.zhenhao.bean.User;
import com.zhenhao.mapper.UserMapper;
import com.zhenhao.mapper.UserPermissionMapper;
import com.zhenhao.mapper.UserRoleMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserPermissionMapper userPermissionMapper;

    /**
     * ??????????????
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
//        User user = (User) SecurityUtils.getSubject().getPrincipal();
//        String userName = user.getUserName();
//
//        System.out.println("用户" + userName + "获取权限-----ShiroRealm.doGetAuthorizationInfo");
//        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//
//        // 获取用户角色集
//        List<Role> roleList = userRoleMapper.findByUserName(userName);
//        Set<String> roleSet = new HashSet<String>();
//        for (Role r : roleList) {
//            roleSet.add(r.getName());
//        }
//        simpleAuthorizationInfo.setRoles(roleSet);
//
//        // 获取用户权限集
//        List<Permission> permissionList = userPermissionMapper.findByUserName(userName);
//        Set<String> permissionSet = new HashSet<String>();
//        for (Permission p : permissionList) {
//            permissionSet.add(p.getName());
//        }
//        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return null;
    }

    /**
     * ??????
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // ??????????????????????
        String userName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        System.out.println("???" + userName + "???-----ShiroRealm.doGetAuthenticationInfo");

        // ???????????????????????
        User user = userMapper.findByUserName(userName);

        if (user == null) {
            throw new UnknownAccountException("??????????????");
        }
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("??????????????");
        }
        if (user.getStatus().equals("0")) {
            throw new LockedAccountException("??????????,????????????");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }
}
