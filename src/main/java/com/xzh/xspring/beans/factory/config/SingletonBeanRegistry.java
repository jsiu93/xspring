package com.xzh.xspring.beans.factory.config;

public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);
}
