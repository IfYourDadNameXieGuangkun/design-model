package exception.aye.ex.enums;


import org.springframework.http.HttpStatus;

/**
 * @ClassName BizErrorEnum
 * @Description [服务器异常]处理服务器异常
 * @Author Aye
 * @Date 2020/9/1 11:36
 * @Version 1.0
 */
public enum ServletErrorEnum implements IErrorEnum {

    /**
     * 服务器异常错误码
     */
    NoHandlerFoundException(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()),
    HttpRequestMethodNotSupportedException(HttpStatus.METHOD_NOT_ALLOWED.value(), HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase()),
    HttpMediaTypeNotSupportedException(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase()),
    MissingPathVariableException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),
    MissingServletRequestParameterException(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()),
    TypeMismatchException(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()),
    HttpMessageNotReadableException(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()),
    HttpMessageNotWritableException(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()),
    HttpMediaTypeNotAcceptableException(HttpStatus.NOT_ACCEPTABLE.value(), HttpStatus.NOT_ACCEPTABLE.getReasonPhrase()),
    ServletRequestBindingException(HttpStatus.NOT_ACCEPTABLE.value(), HttpStatus.NOT_ACCEPTABLE.getReasonPhrase()),
    ConversionNotSupportedException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),
    MissingServletRequestPartException(HttpStatus.NOT_ACCEPTABLE.value(), HttpStatus.NOT_ACCEPTABLE.getReasonPhrase()),
    AsyncRequestTimeoutException(HttpStatus.SERVICE_UNAVAILABLE.value(), HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase()),

    ;

    ServletErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String msg;


    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}