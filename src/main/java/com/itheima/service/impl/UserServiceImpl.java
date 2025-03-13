package com.itheima.service.impl;


import com.itheima.dao.UserDao;
import com.itheima.service.UserService;
import org.springframework.beans.factory.InitializingBean;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class UserServiceImpl implements UserService, InitializingBean {

    public UserServiceImpl(){
        System.out.println("UserService实例化");
    }

    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        System.out.println("实现注入userDao的操作");
        this.userDao = userDao;
    }

    private Set<UserDao> userDaoSet;
    public void setUserDaoSet(Set<UserDao> userDaoSet) {
        this.userDaoSet = userDaoSet;
    }

    private Map<String, UserDao> userDaoMap;
    public void setUserDaoMap(Map<String, UserDao> userDaoMap) {
        this.userDaoMap = userDaoMap;
    }

    private Properties properties;
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void show(){
        System.out.println(userDao);
        System.out.println(userDaoSet);
        System.out.println(userDaoMap);
        System.out.println(properties);
    }

    //在属性配置后调用
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet调用");
    }
}
