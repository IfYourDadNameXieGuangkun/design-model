package spring.core.make1.convert;

import java.lang.reflect.Type;

public class IntegerConvert implements TypeConvert {
    @Override
    public Object doConvert(Class<?> type, String value) {
        if (Integer.class == type) {
            return Integer.valueOf(value);
        }
        return null;
    }
}
