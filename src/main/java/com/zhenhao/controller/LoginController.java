package com.zhenhao.controller;

import com.zhenhao.bean.ResponseBo;
import com.zhenhao.bean.User;
import com.zhenhao.service.UserService;
import com.zhenhao.util.JWTUtil;
import com.zhenhao.util.MD5Utils;
import com.zhenhao.util.RedisUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

@Controller
public class LoginController {


    @Autowired
    UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseBo login(HttpServletRequest request, String username, String password, Boolean rememberMe) {

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ResponseBo.error(-999, "用户名密码为空");
        }

        User user = userService.findByUserName(username);

        if (ObjectUtils.isEmpty(user)) {
            return ResponseBo.error("此用户不存在");
        }

        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token;

        if (rememberMe != null) {
            token = new UsernamePasswordToken(username, password, rememberMe);
        } else {
            token = new UsernamePasswordToken(username, password);
        }
        String authorization = JWTUtil.sign(username, password);

        redisUtils.set(authorization, username, 30L, TimeUnit.MINUTES);

        if (redisUtils.get(username)!=null){
            return ResponseBo.error("您登录操作频繁，请稍后再登录");
        }else{
            redisUtils.set(username,true,1L,TimeUnit.MINUTES);
        }


        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return ResponseBo.ok(authorization);
        } catch (UnknownAccountException e) {
            return ResponseBo.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return ResponseBo.error(e.getMessage());
        } catch (LockedAccountException e) {
            return ResponseBo.error(e.getMessage());
        } catch (AuthenticationException e) {
            return ResponseBo.error("认证失败！");
        }
    }

    @RequestMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }


    @RequestMapping("/index")
    public String index(Model model) {
        // ��¼�ɺ󣬼���ͨ��Subject��ȡ��¼���û���Ϣ
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        return "index";
    }
}
