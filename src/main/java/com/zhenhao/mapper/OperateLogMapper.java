package com.zhenhao.mapper;

import com.zhenhao.bean.OperateLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by zhenhao on 2021/4/20 9:27
 * Content:
 */
@Mapper
public interface OperateLogMapper {


    void insertOperateLog(OperateLog log);
}
