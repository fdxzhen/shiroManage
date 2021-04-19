package com.zhenhao.service.impl;

import com.zhenhao.bean.User;
import com.zhenhao.mapper.UserMapper;
import com.zhenhao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhenhao on 2021/4/19 17:16
 * Content:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User findByUserName(String userName) {

        User user = userMapper.findByUserName(userName);
        return user;

    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }
}
