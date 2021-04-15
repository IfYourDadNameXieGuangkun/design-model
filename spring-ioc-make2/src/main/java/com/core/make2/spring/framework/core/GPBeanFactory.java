package com.core.make2.spring.framework.core;

/**
 * 1.单例工厂的顶层设计
 */
public interface GPBeanFactory {

    /**
     * 根据beanName 从IOC容器中获得一个实例Bean
     *
     * @param beanName
     * @return
     */
    public Object getBean(String beanName) throws Exception;

    public Object getBean(Class<?> beanClass) throws Exception;
}
