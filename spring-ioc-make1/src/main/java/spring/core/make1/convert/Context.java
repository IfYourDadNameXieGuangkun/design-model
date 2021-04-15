package spring.core.make1.convert;

public class Context {
    private TypeConvert typeConvert;

    public Context(TypeConvert typeConvert) {
        this.typeConvert = typeConvert;
    }

    public Object doTypeConvert(Class<?> type, String value) {
        return typeConvert.doConvert(type, value);
    }
}
