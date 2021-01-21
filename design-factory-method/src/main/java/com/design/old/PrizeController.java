package com.design.old;

import com.alibaba.fastjson.JSON;
import com.design.dto.AwardReq;
import com.design.dto.AwardRes;
import com.design.model.card.IQiYiCard;
import com.design.model.coupon.CouponInfo;
import com.design.model.coupon.CouponResult;
import com.design.model.goods.GoodsInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrizeController {
    private Logger logger = LoggerFactory.getLogger(PrizeController.class);
    @Autowired
    private IQiYiCard iQiYiCard;
    @Autowired
    private CouponInfo couponInfo;
    @Autowired
    private GoodsInfo goodsInfo;

    @PostMapping("/getAward")
    public AwardRes awardToUser(AwardReq req) {
        String reqJson = JSON.toJSONString(req);

        // 按照不同类型方法商品[1优惠券、2实物商品、3第三方兑换卡(爱奇艺)]
        logger.info("奖品发放开始[{}]req:{}", req.getUId(), reqJson);
        AwardRes awardRes = null;
        try {
            if (req.getAwardType() == 1) {
                CouponResult couponResult = couponInfo.sendCoupon(req.getUId(), req.getAwardNumber(), req.getBizId());
                logger.info(couponResult.toString());
            }

        } catch (Exception e) {

        }
        return null;
    }
}
