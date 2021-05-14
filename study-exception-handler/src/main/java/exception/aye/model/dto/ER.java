package exception.aye.model.dto;

import java.io.Serializable;

public class ER extends BaseResponse implements Serializable {

    public ER(int code, String message) {
        super(code, message);
    }
}
