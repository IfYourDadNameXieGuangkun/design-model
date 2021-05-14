package exception.aye.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import exception.aye.ex.enums.CommonEnum;
import exception.aye.ex.enums.IErrorEnum;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public abstract class BaseResponse implements Serializable {

    /**
     * 当前返回的时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime timestamp;

    /**
     * 业务编码
     */
    protected int code;

    /**
     * 业务 message
     */
    protected String msg;

    public BaseResponse() {
        this(CommonEnum.SUCCESS);
    }

    public BaseResponse(IErrorEnum errorEnum) {
        this(errorEnum.getCode(), errorEnum.getMsg());
    }

    public BaseResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.timestamp = LocalDateTime.now();
    }

}
