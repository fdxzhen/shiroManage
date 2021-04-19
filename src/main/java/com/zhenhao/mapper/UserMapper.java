package com.zhenhao.mapper;

import com.zhenhao.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User findByUserName(String userName);

    List<String> getPermissionByName(String name);

    void insertUser(User user);
}
