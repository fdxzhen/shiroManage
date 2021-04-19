package com.zhenhao.mapper;

import com.zhenhao.bean.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserPermissionMapper {
    List<Permission> findByUserName(String userName);
}
