package com.zhenhao.service.impl;

import com.zhenhao.bean.OperateLog;
import com.zhenhao.mapper.OperateLogMapper;
import com.zhenhao.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhenhao on 2021/4/20 9:27
 * Content:
 */
public class OperateLogServiceImpl implements OperateLogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public void insertOperateLog(OperateLog log) {
        operateLogMapper.insertOperateLog(log);
    }
}
