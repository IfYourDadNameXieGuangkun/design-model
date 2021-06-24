package com.strategy.支付.common;

import exception.aye.ex.enums.ArgumentErrorEnum;
import exception.aye.ex.enums.CommonEnum;
import exception.aye.ex.enums.ServletErrorEnum;
import exception.aye.ex.exception.BaseException;
import exception.aye.ex.exception.BizException;
import exception.aye.model.dto.ER;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

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
        log.error(profile + "==>" + e.getMessage(), e);
//        log.error(e.getMessage(), e);
        return new ER(e.getErrorEnum().getCode(), getMsg(e));
    }

    /**
     * 自定义异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public ER handleBaseException(BaseException e) {
        log.error(e.getMessage(), e);
        return new ER(e.getErrorEnum().getCode(), getMsg(e));
    }


    /**
     * Controller上一层相关异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler({
            NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpMediaTypeNotAcceptableException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            // BindException.class,
            // MethodArgumentNotValidException.class
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class
    })
    @ResponseBody
    public ER handleServletException(Exception e) {
        log.error(e.getMessage(), e);
        int code = CommonEnum.SERVER_ERROR.getCode();
        try {
            ServletErrorEnum servletErrorEnum = ServletErrorEnum.valueOf(e.getClass().getSimpleName());
            code = servletErrorEnum.getCode();
        } catch (IllegalArgumentException e1) {
            log.error("class [{}] not defined in enum {}", e.getClass().getName(), ServletErrorEnum.class.getName());
        }

        if (ENV_PROD.equals(profile)) {
            // 当为生产环境, 不适合把具体的异常信息展示给用户, 比如404.
            code = CommonEnum.SERVER_ERROR.getCode();
            BaseException baseException = new BaseException(CommonEnum.SERVER_ERROR);
            String message = getMsg(baseException);
            return new ER(code, message);
        }

        return new ER(code, e.getMessage());
    }

    /**
     * 参数绑定异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ER handleBindException(BindException e) {
        log.error("参数绑定校验异常", e);

        return wrapperBindingResult(e.getBindingResult());
    }


    /**
     * 参数校验(Valid)异常，将校验失败的所有异常组合成一条错误信息
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ER handleValidException(MethodArgumentNotValidException e) {
        log.error("参数绑定校验异常", e);
        return wrapperBindingResult(e.getBindingResult());
    }


    /**
     * 未定义异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ER handleException(Exception e) {
        log.error(e.getMessage(), e);

        if (ENV_PROD.equals(profile)) {
            // 当为生产环境, 不适合把具体的异常信息展示给用户, 比如数据库异常信息.
            int code = CommonEnum.SERVER_ERROR.getCode();
            BaseException baseException = new BaseException(CommonEnum.SERVER_ERROR);
            String message = getMsg(baseException);
            return new ER(code, message);
        }

        return new ER(CommonEnum.SERVER_ERROR.getCode(), e.getMessage());
    }


    /**
     * 包装绑定异常结果
     *
     * @param bindingResult 绑定结果
     * @return 异常结果
     */
    private ER wrapperBindingResult(BindingResult bindingResult) {
        StringBuilder msg = new StringBuilder();

        for (ObjectError error : bindingResult.getAllErrors()) {
            msg.append(", ");
            if (error instanceof FieldError) {
                msg.append(((FieldError) error).getField()).append(": ");
            }
            msg.append(error.getDefaultMessage() == null ? "" : error.getDefaultMessage());

        }

        return new ER(ArgumentErrorEnum.VALID_ERROR.getCode(), msg.substring(2));
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
