package exception.aye.ex.assertion;


import exception.aye.ex.enums.IErrorEnum;
import exception.aye.ex.exception.ArgumentException;
import exception.aye.ex.exception.BaseException;

import java.text.MessageFormat;

public interface ArgumentExceptionAssert extends IErrorEnum, Assert {

    @Override
    default BaseException newException(Object... args) {
        String msg = MessageFormat.format(this.getMsg(), args);
        return new ArgumentException(this, msg, args);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = MessageFormat.format(this.getMsg(), args);
        return new ArgumentException(this, msg, t, args);
    }
}
