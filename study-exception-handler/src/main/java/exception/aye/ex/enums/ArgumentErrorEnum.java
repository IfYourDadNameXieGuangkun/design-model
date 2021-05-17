package exception.aye.ex.enums;

import exception.aye.ex.assertion.CommonExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName ArgumentErrorEnum
 * @Description TODO
 * @Author Aye
 * @Date 2020/9/1 17:10
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum ArgumentErrorEnum implements CommonExceptionAssert {
    /**
     * 绑定参数校验异常
     */
    VALID_ERROR(6000, "参数校验异常"),

    ;

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String msg;

}