package com.xzh.xspring.beans.factory.support;

import com.xzh.xspring.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
