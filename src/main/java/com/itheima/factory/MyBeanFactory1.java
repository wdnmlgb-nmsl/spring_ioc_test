package com.itheima.factory;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;

//静态工厂
public class MyBeanFactory1 {
    public static UserDao userDao(){


        // 用工厂方式实例化Bean可以实现在Bean创建之前做一些业务逻辑
        return new UserDaoImpl();
    }
}
