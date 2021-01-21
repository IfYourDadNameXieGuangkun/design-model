package com.design.model.card;

import com.design.model.Aye;
import org.springframework.stereotype.Service;


/**
 * 爱奇艺会员卡服务
 */
@Service
@Aye
public class IQiYiCardService implements IQiYiCard {
    @Override
    public void getIQiYiCard(String bindMobileNumber, String cardId) {
        System.out.println("模拟发放爱奇艺会员卡一张：" + bindMobileNumber + "，" + cardId);
    }
}
