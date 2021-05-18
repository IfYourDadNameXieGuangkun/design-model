package com.core.make2.spring.framework.context.support;

import com.core.make2.spring.framework.beans.config.GPBeanDefinition;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 对配置文件进行查找、读取、解析
 */
public class GPBeanDefinitionReader {
    //放入配置路径中获取到的Bean对象
    private List<String> registerBeanClasses = new ArrayList<String>();
    private Properties config = new Properties();
    //固定配置文件中的key ,相对于XML的规范
    private final String SCAN_PACKAGE = "scanPackage";

    public GPBeanDefinitionReader(String... location) {
        //通过URL定位找到其中所对应的文件,然后转换为文件流
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(location[0].replace("classpath:", ""));
        try {
            config.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        doScanner(config.getProperty(SCAN_PACKAGE));
    }

    private void doScanner(String scanPackage) {
        //转换为文件路径,实际上就是把.替换成/
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {continue;}
                String className = scanPackage + "." + file.getName().replace(".class", "");
                registerBeanClasses.add(className);
            }
        }
    }

    public Properties getConfig() {
        return this.config;
    }

    //把配置文件中扫描到的所有配置信息转换为GPBeanDefinition 对象,以便于之后的IOC操作
    public List<GPBeanDefinition> loadBeanDefinitions() {
        List<GPBeanDefinition> result = new ArrayList<>();
        try {
            for (String className : registerBeanClasses) {
                Class<?> beanClass = Class.forName(className);
                if (beanClass.isInterface()) {continue;}
                result.add(doCreateBeanDefinition(toLowerFirstCase(beanClass.getSimpleName()), beanClass.getName()));
                Class<?>[] interfaces = beanClass.getInterfaces();
                for (Class<?> i : interfaces) {
                    result.add(doCreateBeanDefinition(i.getName(), beanClass.getName()));
                }
            }
        } catch (Exception e) {

        }
        return result;
    }

    /**
     * 把每一个配置信息解析成一个BeanDefinition
     *
     * @param factoryBeanName
     * @param beanClassName
     * @return
     */
    private GPBeanDefinition doCreateBeanDefinition(String factoryBeanName, String beanClassName) {
        GPBeanDefinition beanDefinition = new GPBeanDefinition();
        beanDefinition.setFactoryBeanName(factoryBeanName);
        beanDefinition.setBeanClassName(beanClassName);
        return beanDefinition;
    }

    //将类的首字母改为小写
    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        //大小写字母的ASCII码相差32
        //大写字母的ASCII码要小于ASCII码
        //在JAVA中,对char做算数运算实际上就是对ASCII码作算数运算
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
