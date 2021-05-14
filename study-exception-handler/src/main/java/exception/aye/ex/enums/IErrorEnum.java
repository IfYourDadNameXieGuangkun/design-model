package exception.aye.ex.enums;

/**
 * @ClassName IExceptionEnum
 * @Description 该接口定义了异常信息返回的通用字段
 * @Author Aye
 * @Date 2020/9/1 11:06
 * @Version 1.0
 */
public interface IErrorEnum {

    int getCode();

    String getMsg();
}
