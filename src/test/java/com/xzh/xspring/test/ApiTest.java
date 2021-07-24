package com.xzh.xspring.test;

import com.xzh.xspring.BeanDefinition;
import com.xzh.xspring.BeanFactory;
import com.xzh.xspring.test.bean.UserService;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test_BeanFactory() {
        BeanFactory beanFactory = new BeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(new UserService());

        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService");

        userService.queryUserInfo();

    }
}
