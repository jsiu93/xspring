package com.xzh.xspring.test;

import com.xzh.xspring.beans.BeanException;
import com.xzh.xspring.beans.PropertyValue;
import com.xzh.xspring.beans.PropertyValues;
import com.xzh.xspring.beans.factory.config.BeanDefinition;
import com.xzh.xspring.beans.factory.BeanFactory;
import com.xzh.xspring.beans.factory.config.BeanReference;
import com.xzh.xspring.beans.factory.support.DefaultListableBeanFactory;
import com.xzh.xspring.beans.factory.support.DefaultSingletonBeanRegistry;
import com.xzh.xspring.test.bean.UserDao;
import com.xzh.xspring.test.bean.UserService;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test_BeanFactory() throws BeanException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "2"));
        propertyValues.addPropertyValue(new PropertyValue("userDao" , new BeanReference("userDao")));

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);

        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService");

        userService.queryUserInfo();

    }
}
