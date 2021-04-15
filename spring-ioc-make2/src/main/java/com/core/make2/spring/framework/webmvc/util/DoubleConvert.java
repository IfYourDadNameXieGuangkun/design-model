package com.core.make2.spring.framework.webmvc.util;

public class DoubleConvert implements TypeConvert {
    @Override
    public Object doConvert(Class<?> type, String value) {
        if (Double.class == type) {
            return Double.valueOf(value);
        }
        return null;
    }
}
