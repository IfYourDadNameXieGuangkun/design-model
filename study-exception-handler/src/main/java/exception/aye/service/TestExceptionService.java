package exception.aye.service;

import exception.aye.ex.enums.BizErrorEnum;
import exception.aye.ex.enums.CommonEnum;
import exception.aye.ex.exception.BizException;
import exception.aye.model.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestExceptionService implements ITestExceptionService {
    @Override
    public User test01(String name) {
       try {
           Integer integer = 1/0;
           log.info("异常校验进入===>{}", name);
           if ("aye".equals(name)) {
               throw new BizException(BizErrorEnum.PRODUCT_CODE_ERROR);
           }
           return User.builder()
                   .age("18")
                   .id("123")
                   .name("aye").build();
       }catch (Exception e){
           throw new BizException(CommonEnum.SERVER_ERROR);
       }
    }
}
