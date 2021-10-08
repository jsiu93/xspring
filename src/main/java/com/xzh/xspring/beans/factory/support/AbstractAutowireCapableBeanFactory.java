package com.xzh.xspring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.xzh.xspring.beans.BeanException;
import com.xzh.xspring.beans.PropertyValue;
import com.xzh.xspring.beans.PropertyValues;
import com.xzh.xspring.beans.factory.config.BeanDefinition;
import com.xzh.xspring.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeanException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
            applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception exception) {
            throw new BeanException("Instantiation of bean failed", exception);
        }
        addSingleton(beanName, bean);
        return bean;
    }

    private void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) throws BeanException {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;

                    value = getBean(beanReference.getBeanName());
                }

                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeanException("Error setting property values: " + beanName);
        }

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
