package spring.core.make1.convert;

public class LongConvert implements TypeConvert {
    @Override
    public Object doConvert(Class<?> type, String value) {
        if (Long.class == type) {
            return Long.valueOf(value);
        }
        return null;
    }
}
