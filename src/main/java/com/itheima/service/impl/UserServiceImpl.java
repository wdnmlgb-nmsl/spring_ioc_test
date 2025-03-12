package com.itheima.service.impl;


import com.itheima.dao.UserDao;
import com.itheima.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        System.out.println("BeanFactory去调用该方法获得userDao设置到此处:" + userDao);
        this.userDao = userDao;
    }
}
