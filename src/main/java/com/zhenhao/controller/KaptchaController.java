package com.zhenhao.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.zhenhao.util.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by zhenhao on 2021/4/20 11:06
 * Content:
 */
@Controller
public class KaptchaController {


    @Autowired
    DefaultKaptcha defaultKaptcha;
    @RequestMapping("/kaptcha")
    public void generateNumber(HttpServletRequest request,
                               HttpServletResponse response) {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        //1、 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);

        //2、 删除以前的验证码
        request.getSession().setAttribute("verifyCode", verifyCode);
        System.out.println("verifyCode" +verifyCode+"--------------------");
        request.getSession().setAttribute("verifyTime", new Date());

        //3、 生成图片
        int w = 80, h = 30;
        try {
            VerifyCodeUtils.outputImage(w, h, response.getOutputStream(),
                    verifyCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
