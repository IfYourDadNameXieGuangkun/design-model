package exception.aye.ex;

import exception.aye.ex.exception.BaseException;
import exception.aye.ex.exception.BizException;
import exception.aye.model.dto.ER;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalDefaultExceptionHandler {

    /**
     * 生产环境
     */
    private final static String ENV_PROD = "prod";
    /**
     * 当前环境
     */
    @Value("${spring.profiles.active}")
    private String profile;


    /**
     * 业务异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public ER handleBusinessException(BaseException e) {
        log.error(profile+"==>"+e.getMessage(), e);
//        log.error(e.getMessage(), e);
        return new ER(e.getErrorEnum().getCode(), getMsg(e));
    }

    /**
     * 异常msg处理
     *
     * @param e 异常
     * @return
     */
    public String getMsg(BaseException e) {
        String msg = e.getErrorEnum().getMsg();
        if (msg == null || msg.isEmpty()) {
            return e.getMessage();
        }
        return msg;
    }
}
