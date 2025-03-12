package com.itheima.service.impl;


import com.itheima.dao.UserDao;
import com.itheima.service.UserService;
import org.springframework.beans.factory.InitializingBean;

public class UserServiceImpl implements UserService, InitializingBean {

    public UserServiceImpl(){
        System.out.println("UserServiceImpl实例化");
    }

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    //在属性配置后调用
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet调用");
    }
}
