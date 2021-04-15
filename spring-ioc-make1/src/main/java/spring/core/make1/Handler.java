package spring.core.make1;

import lombok.Data;
import spring.core.make1.annotation.GPRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

//@Data
public class Handler {
    protected Object controller;//保存方法对应的实例
    protected Method method;//保存映射的方法
    protected Pattern pattern;//正则
    protected Map<String, Integer> paramsIndexMapping;//参数顺序

    public Handler(Pattern pattern, Object controller, Method method) {
        this.controller = controller;
        this.method = method;
        this.pattern = pattern;
        paramsIndexMapping = new HashMap<String, Integer>();
        putParamsIndexMapping(method);
    }

    private void putParamsIndexMapping(Method method) {
        //提取参数中加入了注解的参数
        Annotation[][] params = method.getParameterAnnotations();
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

        //提取方法中的request 和 response 参数
        Class<?>[] parameterTypes = method.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> type = parameterTypes[i];
            if (type == HttpServletRequest.class ||
                    type == HttpServletResponse.class) {
                paramsIndexMapping.put(type.getName(), i);
            }
        }
    }
}
