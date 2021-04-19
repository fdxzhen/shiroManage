package com.zhenhao.interceptor;

import com.zhenhao.mapper.UserMapper;
import com.zhenhao.util.RedisUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by zhenhao on 2021/4/16 14:30
 * Content:
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURI();
        if (request.getServletPath().equals("/login")|| request.getServletPath().contains(".js")|| request.getServletPath().contains(".css")) {
            return true;
        }

        String authorization =  request.getHeader("authorization");

        if (authorization != null) {
            String username = (String) redisUtils.get(authorization);
            if (StringUtils.isEmpty(username)){
                return false;
            }
            List<String> permissionList = userMapper.getPermissionByName(username);
            if (StringUtils.isEmpty(permissionList)){
                return false;
            }
            System.out.println("permissionList:" + permissionList);
            if (permissionList.contains(url)) {
                return true;
            }
        }
        response.sendRedirect("/login");
        return false;


    }
}
