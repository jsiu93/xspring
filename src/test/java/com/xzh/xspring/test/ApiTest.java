package com.xzh.xspring.test;

import com.xzh.xspring.beans.BeanException;
import com.xzh.xspring.beans.factory.config.BeanDefinition;
import com.xzh.xspring.beans.factory.BeanFactory;
import com.xzh.xspring.beans.factory.support.DefaultListableBeanFactory;
import com.xzh.xspring.beans.factory.support.DefaultSingletonBeanRegistry;
import com.xzh.xspring.test.bean.UserService;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test_BeanFactory() throws BeanException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);

        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService", "xxx");
        userService.queryUserInfo();

//        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
//        userService_singleton.queryUserInfo();
    }
}
