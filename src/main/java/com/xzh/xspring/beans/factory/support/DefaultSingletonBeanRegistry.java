package com.xzh.xspring.beans.factory.support;

import com.xzh.xspring.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singleObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return this.singleObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        this.singleObjects.put(beanName, singletonObject);
    }
}
