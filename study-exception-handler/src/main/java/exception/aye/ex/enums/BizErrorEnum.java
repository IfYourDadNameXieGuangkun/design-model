package exception.aye.ex.enums;

/**
 * @ClassName BizErrorEnum
 * @Description [业务异常]自定义业务异常错误码,主要处理业务异常错误
 * @Author Aye
 * @Date 2020/9/1 11:36
 * @Version 1.0
 */
public enum BizErrorEnum implements IErrorEnum {
    /**
     * 业务异常错误码,可以增量自定义
     */
    PRODUCT_CODE_SUCCESS(1000, "成功"),
    PRODUCT_CODE_NONE_ERROR(1001, "商品不存在"),
    PRODUCT_CODE_ERROR(1001, "商品异常"),

    ;

    private int code;
    private String msg;

    BizErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
