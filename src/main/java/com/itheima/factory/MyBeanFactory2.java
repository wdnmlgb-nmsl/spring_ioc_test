package com.itheima.factory;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;

//实例工厂适用于根据对象状态或其他条件来决定创建什么样的实例
public class MyBeanFactory2 {
    public UserDao userDao(){
        return new UserDaoImpl();
    }
}
