package com.xzh.xspring.beans.factory;

import com.xzh.xspring.beans.BeanException;
import com.xzh.xspring.beans.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface BeanFactory {

    Object getBean(String name) throws BeanException;

    Object getBean(String name, Object... args) throws BeanException;

}
