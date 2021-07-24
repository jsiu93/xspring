package com.xzh.xspring.beans.factory.support;

import com.xzh.xspring.beans.BeanException;
import com.xzh.xspring.beans.factory.config.BeanDefinition;

public abstract class AbstractAutowireCapableBeanFactory extends  AbstractBeanFactory{

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeanException {
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException exception) {
            throw new BeanException("Instantiation of bean failed", exception);
        }
        addSingleton(beanName, bean);
        return bean;
    }
}
