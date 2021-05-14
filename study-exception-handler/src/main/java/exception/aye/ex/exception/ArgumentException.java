package exception.aye.ex.exception;

import exception.aye.ex.enums.IErrorEnum;

/**
 * @ClassName ArgumentException
 * @Description
 * <p>参数异常</p>
 *  <p>在处理业务过程中校验参数出现错误, 可以抛出该异常</p>
 *  <p>编写公共代码（如工具类）时，对传入参数检查不通过时，可以抛出该异常</p>
 * @Author Aye
 * @Date 2020/9/1 16:04
 * @Version 1.0
 */
public class ArgumentException extends BaseException {
    public ArgumentException(IErrorEnum errorEnum) {
        super(errorEnum);
    }

    public ArgumentException(IErrorEnum errorEnum, String msg, Object... args) {
        super(errorEnum, msg, args);
    }

    public ArgumentException(IErrorEnum errorEnum, String msg, Throwable cause, Object... args) {
        super(errorEnum, msg, cause, args);
    }
}
