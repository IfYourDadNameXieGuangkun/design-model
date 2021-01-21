package com.design.New;

import com.design.dto.AwardReq;
import com.design.dto.AwardRes;
import com.design.model.card.IQiYiCard;
import com.design.model.coupon.CouponInfo;
import com.design.model.coupon.CouponResult;
import com.design.model.goods.GoodsInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponAward implements IAward {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CouponInfo couponInfo;
    @Autowired
    private GoodsInfo goodsInfo;
    @Override
    public AwardRes sendAward(AwardReq req) {
        CouponResult couponResult = couponInfo.sendCoupon(req.getUId(), req.getAwardNumber(), req.getBizId());
        logger.info(couponResult.toString());
        if (couponResult.getCode().equals("0000")) {
            return AwardRes.builder().code("0000").info(req.getAwardType() + "-发放成功").build();
        } else {
            return AwardRes.builder().code("0000").info(req.getAwardType() + "-发放失败").build();
        }
    }

}
