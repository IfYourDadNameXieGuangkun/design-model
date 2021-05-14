package exception.aye.ex.assertion;

import exception.aye.ex.enums.IErrorEnum;
import exception.aye.ex.exception.BaseException;
import exception.aye.ex.exception.BizException;

import java.text.MessageFormat;

public interface BizExceptionAssert extends Assert, IErrorEnum {
    @Override
    default BaseException newException(Object... args) {
        String msg = MessageFormat.format(this.getMsg(), args);
        return new BizException(this, msg, args);
    }

    @Override
    default BaseException newException(Throwable cause, Object... args) {
        String msg = MessageFormat.format(this.getMsg(), args);
        return new BizException(this, msg, cause, args);
    }

}
