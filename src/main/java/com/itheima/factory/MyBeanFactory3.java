package com.itheima.factory;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;
import org.springframework.beans.factory.FactoryBean;

//实现FactoryBean规范延迟实例化Bean，单例，创建容器时就实例化了BeanFactory并存储在了FactoryBeanObjectCache中，当需要获取Bean时再通过工厂的getObject去实例对象
public class MyBeanFactory3 implements FactoryBean<UserDao> {

    @Override
    public UserDao getObject() throws Exception {
        return new UserDaoImpl();
    }

    @Override
    public Class<?> getObjectType() {
        return UserDao.class;
    }
}
