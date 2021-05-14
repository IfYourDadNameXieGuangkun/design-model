package exception.aye.ex.assertion;

import exception.aye.ex.exception.BaseException;

public interface Assert {
    BaseException newException(Object... args);

    BaseException newException(Throwable cause, Object... args);

    default void assertNotNull(Object object) {
        if (object == null) {
            throw newException();
        }
    }
}
