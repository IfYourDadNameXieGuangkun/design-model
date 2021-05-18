package com.core.make2.spring.framework.webmvc;

import com.core.make2.spring.framework.annotation.GPRequestParam;
import com.core.make2.spring.framework.webmvc.util.Context;
import com.core.make2.spring.framework.webmvc.util.DoubleConvert;
import com.core.make2.spring.framework.webmvc.util.IntegerConvert;
import com.core.make2.spring.framework.webmvc.util.LongConvert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 专人干专事
 */
public class GPHandlerAdapter {
    public Boolean support(Object handler) {
        return (handler instanceof GPHandlerMapping);
    }

    public GPModelAndView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        GPHandlerMapping handlerMapping = (GPHandlerMapping) handler;

        //每个方法有一个参数列表,这里保存的是形参列表
        Map<String, Integer> paramsIndexMapping = new HashMap<>();

        //这里只是给出命名参数
        Annotation[][] params = handlerMapping.method.getParameterAnnotations();
        for (int i = 0; i < params.length; i++) {
            for (Annotation annotation : params[i]) {
                if (annotation instanceof GPRequestParam) {
                    String paramName = ((GPRequestParam) annotation).value();
                    if (!"".equals(paramName.trim())) {
                        paramsIndexMapping.put(paramName, i);
                    }
                }
            }
        }
        //根据用户请求的参数信息,与Method中的参数信息进行动态匹配
        //resp 传进来只有一个目的:将其赋值给方法参数,仅此而已
        //只有当用户传过来的ModelAndView为空的时候,才会新建一个默认的

        //1.要准备好这个方法的形参列表
        //方法重载时形参的决定因素:参数的个数,类型,顺序,方法的名字
        //只处理Request 和Response
        Class<?>[] parameterTypes = handlerMapping.getMethod().getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> type = parameterTypes[i];
            if (type == HttpServletRequest.class ||
                    type == HttpServletResponse.class) {
                paramsIndexMapping.put(type.getName(), i);
            }
        }

        //2.得到自定义命名参数所在的位置
        //用户通过URL传过来的参数列表
        Map<String, String[]> reqParameterMap = req.getParameterMap();

        //3.构造实参列表
        Object[] paramsValues = new Object[parameterTypes.length];

        for (Map.Entry<String, String[]> param : reqParameterMap.entrySet()) {
            String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll("\\s", ",");
            if (!paramsIndexMapping.containsKey(param.getKey())) {continue;}
            int index = paramsIndexMapping.get(param.getKey());
            //因为页面传过来的值都是String 类型的,而在方法中定义的类型是千变万化的
            //所以要针对我们传过来的参数进行类型转换
            paramsValues[index] = convert(parameterTypes[index], value);
            if (paramsIndexMapping.containsKey(HttpServletRequest.class.getName())) {
                Integer reqIndex = paramsIndexMapping.get(HttpServletRequest.class.getName());
                paramsValues[reqIndex] = req;
            }
            if (paramsIndexMapping.containsKey(HttpServletResponse.class.getName())) {
                Integer respIndex = paramsIndexMapping.get(HttpServletResponse.class.getName());
                paramsValues[respIndex] = resp;
            }

        }
        //4.从handler中取出Controller、Method,然后利用反射机制进行调用
        Object result = handlerMapping.getMethod().invoke(handlerMapping.getController(), paramsValues);
        if (!Optional.ofNullable(result).isPresent()) {
            return null;
        }
        Boolean isModelAndView = handlerMapping.getMethod().getReturnType() == GPModelAndView.class;
        if (isModelAndView) {
            return (GPModelAndView) result;
        } else {
            return null;
        }
    }

    /**
     * @param type
     * @param value
     * @return
     * @desc url传过来的参数都是String 类型,由于Http基于字符串协议,只需要把String 转换为任意类型 double Integer Long
     * @design:设计模式:策略设计模式
     */
    private Object convert(Class<?> type, String value) {
        if (type == Integer.class) {
            return new Context(new IntegerConvert()).doTypeConvert(type, value);
        }
        if (type == Long.class) {
            return new Context(new LongConvert()).doTypeConvert(type, value);
        }
        if (type == Double.class) {
            return new Context(new DoubleConvert()).doTypeConvert(type, value);
        }
        return value;
    }
}


