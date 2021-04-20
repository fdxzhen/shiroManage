package com.zhenhao.bean;

import lombok.Data;

import java.util.Date;

/**
 * Created by zhenhao on 2021/4/20 9:15
 * Content:
 */
@Data
public class OperateLog {

    private Long logId;

    private String loginUser;

    private String requestURL;

    private String requestParam;

    private String requestIP;

    private String requestType;

    private String requestClassMethod;

    private Date opeTime;

}
