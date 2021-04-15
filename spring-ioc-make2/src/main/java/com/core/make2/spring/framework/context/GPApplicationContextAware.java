package com.core.make2.spring.framework.context;

/**
 * 相信很多“小伙伴”都用过ApplicationContextAware接口，主要是通
 * 过实现侦听机制得到一个回调方法，从而得到IoC容器的上下文，即
 * ApplicationContext。在这个Mini版本中只是做了一个顶层设计，告诉大
 * 家这样一种现象，并没有做具体实现。这不是本书的重点，感兴趣
 * 的“小伙伴”可以自行尝试
 */
public interface GPApplicationContextAware {
    void setApplicationContext(GPApplicationContext applicationContext);
}
