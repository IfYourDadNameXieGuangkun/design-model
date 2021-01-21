package com.design.New;

import com.alibaba.fastjson.JSON;
import com.design.dto.AwardReq;
import com.design.dto.AwardRes;
import com.design.model.card.IQiYiCard;
import com.design.model.coupon.CouponInfo;
import com.design.model.coupon.CouponResult;
import com.design.model.goods.DeliverReq;
import com.design.model.goods.GoodsInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrizeNewController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IQiYiCard iQiYiCard;
    @Autowired
    private CouponInfo couponInfo;
    @Autowired
    private GoodsInfo goodsInfo;

    @PostMapping("/getNewAward")
    public AwardRes awardToUser(@RequestBody AwardReq req) {
        AwardFactory awardFactory = new AwardFactory();
        IAward award = awardFactory.createAward(req.getAwardType());
        return award.sendAward(req);
    }

    private String queryUserName(String uId) {
        return "花花";
    }

    private String queryUserPhoneNumber(String uId) {
        return "15200101232";
    }
}
