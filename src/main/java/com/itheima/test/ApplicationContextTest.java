package com.itheima.test;

import com.itheima.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextTest {

    public static void main(String[] args) {
        // ApplicationContext是相较于BeanFactory更加高级的接口，在对BeanFactory封装的基础上添加了监听、国际化功能
        // 两者Bean的初始化时间不同，前者在容器一创建就将Bean都实例化并初始化好；后者在首次调用getBean时才创建Bean
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Object userDao1 = applicationContext.getBean("myBeanFactory3");
        System.out.println(userDao1);
    }

}
