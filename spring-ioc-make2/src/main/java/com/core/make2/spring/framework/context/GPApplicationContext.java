package com.core.make2.spring.framework.context;

import com.core.make2.spring.framework.annotation.GPAutowired;
import com.core.make2.spring.framework.annotation.GPController;
import com.core.make2.spring.framework.annotation.GPService;
import com.core.make2.spring.framework.beans.GPBeanWrapper;
import com.core.make2.spring.framework.beans.config.GPBeanDefinition;
import com.core.make2.spring.framework.beans.config.GPBeanPostProcessor;
import com.core.make2.spring.framework.context.support.GPBeanDefinitionReader;
import com.core.make2.spring.framework.context.support.GPDefaultListableBeanFactory;
import com.core.make2.spring.framework.core.GPBeanFactory;
import com.sun.org.apache.regexp.internal.RE;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ApplicationContext 是直接接触用户的入口，主要实现
 * DefaultListableBeanFactory 的 refresh（）方法和BeanFactory的
 * getBean（）方法，完成IoC、 DI、 AOP的衔接
 */

public class GPApplicationContext extends GPDefaultListableBeanFactory implements GPBeanFactory {
    private String[] configLocations;
    private GPBeanDefinitionReader reader;

    //单例的IOC容器缓存
    private Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<String, Object>();

    //通用Ioc容器,保存GPBeanWrapper的缓存
    private Map<String, GPBeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<String, GPBeanWrapper>();

    public GPApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
        try {
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refresh() throws Exception {
        //1.定位配置文件
        reader = new GPBeanDefinitionReader(this.configLocations);
        //2.加载配置文件,扫描相关的类,把它们封装成BeanDefinition
        List<GPBeanDefinition> beanDefinitions = reader.loadBeanDefinitions();
        //3.注册,吧配置文件信息放到容器中(伪IOC容器)
        doRegisterBeanDefinition(beanDefinitions);
        //4.把不是延迟加载的类提前初始化
        doAutowired();
    }


    private void doRegisterBeanDefinition(List<GPBeanDefinition> beanDefinitions) throws Exception {
        for (GPBeanDefinition beanDefinition : beanDefinitions) {
            if (super.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())) {
                throw new Exception("The “" + beanDefinition.getFactoryBeanName() + "” is exists!!");
            }
            super.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
        }
        //到此为止,容器化初始化完毕
    }

    //只处理非延时加载的情况
    private void doAutowired() {
        for (Map.Entry<String, GPBeanDefinition> beanDefinitionEntry : super.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            if (!beanDefinitionEntry.getValue().isLazyInit()) {
                try {
                    getBean(beanName);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }


    @Override
    public Object getBean(Class<?> beanClass) throws Exception {
        return getBean(beanClass.getName());
    }

    /**
     * 依赖注入,从这里开始,读取BeanDefinition中的信息
     * 然后通过反射机制创建一个实例并返回
     * Spring的做法是不会吧最原始的对象放出去,会用一个BeanWrapper来进行一次包装
     * 装饰器模式:
     * 1.保留原来的OOP关系
     * 2.需要对它进行扩展,增强(为了以后AOP打基础)
     *
     * @param beanName
     * @return
     * @throws Exception
     */
    @Override
    public Object getBean(String beanName) {
        try {
            GPBeanDefinition beanDefinition = super.beanDefinitionMap.get(beanName);
            //生成通知事件
            GPBeanPostProcessor beanPostProcessor = new GPBeanPostProcessor();
            Object instance = instantiateBean(beanDefinition);
            if (null == instance) {
                return null;
            }
            //再实例化之前调用一次
            beanPostProcessor.postProcessBeforeInitialization(instance, beanName);

            GPBeanWrapper beanWrapper = new GPBeanWrapper(instance);

            this.factoryBeanInstanceCache.put(beanName, beanWrapper);

            //再实例化之后调用一次
            beanPostProcessor.postProcessAfterInitialization(instance, beanName);

            populateBean(beanName, instance);

            //通过这样调用,相当于给我们自己留有了可操作的空间
            return this.factoryBeanInstanceCache.get(beanName).getWrappedInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void populateBean(String beanName, Object instance) {
        Class clazz = instance.getClass();
        if (!(clazz.isAnnotationPresent(GPController.class) ||
                clazz.isAnnotationPresent(GPService.class))) {
            return;
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(GPAutowired.class)) {
                continue;
            }
            GPAutowired autowired = field.getAnnotation(GPAutowired.class);
            String autowiredBeanName = autowired.value().trim();
            if ("".equals(autowiredBeanName)) {
                autowiredBeanName = field.getType().getName();
            }
            field.setAccessible(true);
            try {
                field.set(instance, this.factoryBeanInstanceCache.get(autowiredBeanName).getWrappedInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //传入一个BeanDefinition 就返回一个实例Bean
    private Object instantiateBean(GPBeanDefinition beanDefinition) {
        Object instance = null;
        String className = beanDefinition.getBeanClassName();
        try {
            if (this.factoryBeanObjectCache.containsKey(className)) {
                instance = this.factoryBeanObjectCache.get(className);
            } else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                this.factoryBeanObjectCache.put(beanDefinition.getFactoryBeanName(),instance);
            }
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }

    public Properties getConfig() {
        return this.reader.getConfig();
    }

}
