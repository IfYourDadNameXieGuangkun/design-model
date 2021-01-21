package com.design.New;

import com.design.dto.AwardReq;
import com.design.dto.AwardRes;
import com.design.model.card.IQiYiCard;
import com.design.model.coupon.CouponInfo;
import com.design.model.goods.GoodsInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardAward implements IAward {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IQiYiCard iQiYiCard;
    @Autowired
    private CouponInfo couponInfo;
    @Autowired
    private GoodsInfo goodsInfo;
    @Override
    public AwardRes sendAward(AwardReq awardReq) {
        String bindMobileNumber = queryUserPhoneNumber(awardReq.getUId());
        iQiYiCard.getIQiYiCard(bindMobileNumber, awardReq.getAwardNumber());
        return AwardRes.builder().code("0000").info(awardReq.getAwardType() + "-发放成功").build();
    }

    private String queryUserName(String uId) {
        return "花花";
    }

    private String queryUserPhoneNumber(String uId) {
        return "15200101232";
    }
}
