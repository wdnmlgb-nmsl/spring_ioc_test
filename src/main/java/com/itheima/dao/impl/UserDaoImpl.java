package com.itheima.dao.impl;

import com.itheima.dao.UserDao;
import org.springframework.beans.factory.InitializingBean;

public class UserDaoImpl implements UserDao, InitializingBean {
    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public UserDaoImpl() {
        System.out.println("UserDao实例化");
    }

    // 执行顺序为实例化->Bean后处理器的初始化前方法->InitializingBean接口的重写方法->init-method->Bean后处理器的初始化后方法
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("属性设置之后");
    }

    @Override
    public void show() {
        System.out.println("show...");
    }
}
