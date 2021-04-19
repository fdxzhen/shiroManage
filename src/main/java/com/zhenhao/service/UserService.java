package com.zhenhao.service;

import com.zhenhao.bean.User;

/**
 * Created by zhenhao on 2021/4/19 17:16
 * Content:
 */
public interface UserService {

    User findByUserName(String userName);

    void insertUser(User user);
}
