package spring.core.make1.convert;


/**
 * 类型转化,策略模式
 */
public interface TypeConvert {
    Object doConvert(Class<?> type, String value);
}
