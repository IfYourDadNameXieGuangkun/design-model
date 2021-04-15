package com.core.make2.spring.framework.webmvc.util;

public class IntegerConvert implements TypeConvert {
    @Override
    public Object doConvert(Class<?> type, String value) {
        if (Integer.class == type) {
            return Integer.valueOf(value);
        }
        return null;
    }
}
