package com.core.make2.spring.framework.beans;

/**
 * BeanWrapper 主要用于封装创建后的对象实例，代理对象（Proxy
 * Object）或者原生对象（Original Object）都由BeanWrapper来保存
 */
public class GPBeanWrapper {
    private Object wrappedInstance;
    private Class<?> wrappedClass;

    public GPBeanWrapper(Object wrappedInstance) {
        this.wrappedInstance = wrappedInstance;
    }

    public Object getWrappedInstance() {
        return wrappedInstance;
    }


    /**
     * 返回代理以后的Class
     * 肯呢个回事这个$proxy0
     *
     * @return
     */
    public Class<?> getWrappedClass() {
        return this.wrappedInstance.getClass();
    }
}
