package com.core.make2.spring.framework.context.support;

import com.core.make2.spring.framework.beans.config.GPBeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DefaultListableBeanFactory是众多IoC容器子类的典型代表
 */
public class GPDefaultListableBeanFactory extends GPAbstractApplicationContext {
    //存储注册信息的BeanDefinition
    protected final Map<String, GPBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, GPBeanDefinition>();
}
