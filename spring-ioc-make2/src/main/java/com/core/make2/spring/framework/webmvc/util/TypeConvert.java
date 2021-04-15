package com.core.make2.spring.framework.webmvc.util;


/**
 * 类型转化,策略模式
 */
public interface TypeConvert {
    Object doConvert(Class<?> type, String value);
}
