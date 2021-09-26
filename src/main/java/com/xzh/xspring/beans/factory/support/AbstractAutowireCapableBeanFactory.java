package com.xzh.xspring.beans.factory.support;

import com.xzh.xspring.beans.BeanException;
import com.xzh.xspring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends  AbstractBeanFactory{

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeanException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
        } catch (Exception exception) {
            throw new BeanException("Instantiation of bean failed", exception);
        }
        addSingleton(beanName, bean);
        return bean;
    }


    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) throws BeanException {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();

        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();

        for (Constructor ctor : declaredConstructors) {
//            这里我们对比的方式比较简单，只是一个数量对比，而实际 Spring 源码中还需要比对入参类型，否则相同数量不同入参类型的情况，就会抛异常了。
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }

        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);


    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }
}
