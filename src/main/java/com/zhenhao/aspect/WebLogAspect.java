package com.zhenhao.aspect;

import com.zhenhao.bean.OperateLog;
import com.zhenhao.mapper.OperateLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by zhenhao on 2021/4/16 17:23
 * Content:
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;


    @Pointcut("execution(public * com.zhenhao.controller..*.*(..))")
    public void controllerMethod() {

    }


    @After("controllerMethod()")
    public void Log(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        HttpSession session = request.getSession();

        Signature signature = joinPoint.getSignature();

        OperateLog operateLog = new OperateLog();
        operateLog.setLogId(System.currentTimeMillis());
        operateLog.setLoginUser((String) session.getAttribute("username"));
        operateLog.setRequestURL(request.getRequestURI());
        operateLog.setRequestParam(request.getQueryString());
        operateLog.setRequestType(request.getMethod());
        operateLog.setRequestClassMethod(signature.getDeclaringTypeName() +"."+ signature.getName());
        operateLog.setRequestIP(request.getRemoteAddr());
        operateLog.setOpeTime(new Date());

        operateLogMapper.insertOperateLog(operateLog);


//        StringBuilder requestLog = new StringBuilder();
//        requestLog.append("???????????????").append("URL = {").append(request.getRequestURI()).append("},\t")
//                .append("???????????? = {").append(request.getMethod()).append("},\t")
//                .append("??????IP = {").append(request.getRemoteAddr()).append("},\t")
//                .append("????????? = {").append(signature.getDeclaringTypeName()).append(".")
//                .append(signature.getName()).append("},\t");
//        System.out.println(requestLog);
//        // ??????????????????
//        String[] paramNames = ((MethodSignature) signature).getParameterNames();
//        Object[] paramValues = joinPoint.getArgs();
//        int paramLength = null == paramNames ? 0 : paramNames.length;
//        if (paramLength == 0) {
//            requestLog.append("???????????? = {} ");
//        } else {
//            requestLog.append("???????????? = [");
//            for (int i = 0; i < paramLength - 1; i++) {
//                requestLog.append(paramNames[i]).append("=").append(JSONObject.toJSONString(paramValues[i])).append(",");
//            }
//            requestLog.append(paramNames[paramLength - 1]).append("=").append(JSONObject.toJSONString(paramValues[paramLength - 1])).append("]");
//        }
//
//        log.info(requestLog.toString());
    }

}
