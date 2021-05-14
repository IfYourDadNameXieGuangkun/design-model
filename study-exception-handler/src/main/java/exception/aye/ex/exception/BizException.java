package exception.aye.ex.exception;

import exception.aye.ex.enums.IErrorEnum;

public class BizException extends BaseException {
    public BizException(IErrorEnum errorEnum) {
        super(errorEnum);
    }

    public BizException(IErrorEnum errorEnum, String msg, Object... args) {
        super(errorEnum, msg, args);
    }

    public BizException(IErrorEnum errorEnum, String msg, Throwable cause, Object... args) {
        super(errorEnum, msg, cause, args);
    }

    public BizException(int code, String msg) {
        super(code, msg);
    }
}
