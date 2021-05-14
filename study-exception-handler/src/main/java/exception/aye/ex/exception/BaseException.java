package exception.aye.ex.exception;

import exception.aye.ex.enums.IErrorEnum;
import lombok.Getter;


/**
 * @ClassName BaseException
 * @Description 异常基类
 * @Author Aye
 * @Date 2020/9/1 11:57
 * @Version 1.0
 */
@Getter
public class BaseException extends RuntimeException {

    protected IErrorEnum errorEnum;
    protected Object[] args;

    public BaseException(IErrorEnum errorEnum) {
        super(errorEnum.getMsg());
        this.errorEnum = errorEnum;
    }

    public BaseException(IErrorEnum errorEnum, String msg, Object... args) {
        super(msg);
        this.errorEnum = errorEnum;
        this.args = args;
    }

    public BaseException(IErrorEnum errorEnum, String msg, Throwable cause, Object... args) {
        super(msg);
        this.errorEnum = errorEnum;
        this.args = args;
    }

    public BaseException(int code, String msg) {
        super(msg);
        this.errorEnum = new IErrorEnum() {
            @Override
            public int getCode() {
                return code;
            }

            @Override
            public String getMsg() {
                return msg;
            }
        };
    }
}
