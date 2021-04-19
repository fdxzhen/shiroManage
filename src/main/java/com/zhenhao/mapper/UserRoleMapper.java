package com.zhenhao.mapper;

import com.zhenhao.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    List<Role> findByUserName(@Param("userName") String userName);
}
