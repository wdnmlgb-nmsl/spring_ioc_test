package com.itheima.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.itheima.dao.PersonDao;
import com.itheima.dao.UserDao;
import com.itheima.factory.MyBeanFactory2;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;
import org.apache.ibatis.io.Resources;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import java.util.Date;
import java.util.Timer;

public class ApplicationContextTest {

    public static void main(String[] args) throws IOException {
        /*// 指定环境
        System.setProperty("spring.profiles.active","dev");

        // ApplicationContext是相较于BeanFactory更加高级的接口，在对BeanFactory封装的基础上添加了监听、国际化功能
        // 两者Bean的初始化时间不同，前者在容器一创建就将Bean都实例化并初始化好；后者在首次调用getBean时才创建Bean
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = applicationContext.getBean("myBeanFactory3",UserDao.class);
        System.out.println(userDao);*/

        /*ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        DruidDataSource dataSource = applicationContext.getBean("dataSource", DruidDataSource.class);
        System.out.println(dataSource);*/


//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gitlog?useSSL=false&serverTimezone=Asia/Shanghai", "root", "123456");
//            System.out.println(connection);
//        } catch (ClassNotFoundException e) {
//            System.out.println(e);
//        } catch (SQLException e) {
//            System.out.println(e);
//        }

        /*// 非自定义类的静态工厂
        Object userDao = applicationContext.getBean("userService_1");
        System.out.println(userDao);*/

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = simpleDateFormat.parse("2025-3-12 21:45:05");

        /*// 非自定义工厂的非静态方法
        Date date = (Date) applicationContext.getBean("date");
        System.out.println(date);

        PersonDao personDao = applicationContext.getBean(PersonDao.class);
        System.out.println(personDao);*/

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
    }

}
