package com.core.make2.spring.framework.webmvc;


import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * 主要用来保存URL和Method的对应关系,这里其实使用的是策略模式
 */
public class GPHandlerMapping {
    protected Object controller;//目标方法所在的controller对象
    protected Method method;//URL对应的目标方法
    protected Pattern pattern;//URL的封装

    public GPHandlerMapping(Pattern pattern, Object controller, Method method) {
        this.controller = controller;
        this.method = method;
        this.pattern = pattern;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }
}
