package com.xzh.xspring.beans.factory.support;

import com.xzh.xspring.beans.BeanException;
import com.xzh.xspring.beans.factory.BeanFactory;
import com.xzh.xspring.beans.factory.config.BeanDefinition;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String name) throws BeanException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeanException {
        return doGetBean(name, args);
    }

    protected <T> T doGetBean(String name, Object[] args) throws BeanException {
        java.lang.Object bean = getSingleton(name);
        if (bean != null) {
            return (T) bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeanException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeanException;
}
