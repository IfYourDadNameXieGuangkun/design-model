package exception.aye.model.dto;

import java.io.Serializable;

/**
 * 通用结果返回
 * @param <T> T
 * @version 1.0
 */
public class CR<T> extends BaseResponse implements Serializable {

    public CR() {
        super();
    }

    public CR(T data) {
        super();
        this.data = data;
    }

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
