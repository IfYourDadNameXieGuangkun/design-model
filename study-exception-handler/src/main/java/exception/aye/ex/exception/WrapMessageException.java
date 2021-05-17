package exception.aye.ex.exception;

/**
 * @ClassName WrapMessageException
 * @Description  只包装了 错误信息 的 {@link RuntimeException}.
 *                 用于 {@link } 中用于包装自定义异常信息
 * @Author Aye
 * @Date 2020/9/1 14:58
 * @Version 1.0
 */
public class WrapMessageException extends RuntimeException  {
    public WrapMessageException(String message) {
        super(message);
    }

    public WrapMessageException(String message, Throwable cause) {
        super(message, cause);
    }
}