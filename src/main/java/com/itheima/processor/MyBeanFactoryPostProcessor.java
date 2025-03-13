package com.itheima.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor, BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("BeanDefinitionRegistryPostProcessor的postProcessBeanFactory...");
//        // 注册BeanDefinition
//        BeanDefinition beanDefinition = new RootBeanDefinition();
//        beanDefinition.setBeanClassName("com.itheima.dao.impl.PersonDaoImpl");
//        // 强转成DefaultListableBeanFactory
//        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;
//        defaultListableBeanFactory.registerBeanDefinition("PersonDao",beanDefinition);
        // 修改某个BeanDefinition
        // BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService_1");
        // beanDefinition.setBeanClassName("com.itheima.dao.impl.UserDaoImpl");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        System.out.println("BeanDefinitionRegistryPostProcessor的postProcessBeanDefinitionRegistry...");
        BeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClassName("com.itheima.dao.impl.PersonDaoImpl");
        beanDefinitionRegistry.registerBeanDefinition("PersonDao", beanDefinition);
    }
}
