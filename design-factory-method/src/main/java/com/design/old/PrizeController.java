package com.design.old;

import com.alibaba.fastjson.JSON;
import com.design.dto.AwardReq;
import com.design.dto.AwardRes;
import com.design.model.card.IQiYiCard;
import com.design.model.coupon.CouponInfo;
import com.design.model.coupon.CouponResult;
import com.design.model.goods.DeliverReq;
import com.design.model.goods.GoodsInfo;
import com.design.model.goods.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrizeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IQiYiCard iQiYiCard;
    @Autowired
    private CouponInfo couponInfo;
    @Autowired
    private GoodsInfo goodsInfo;

    @PostMapping("/getAward")
    public AwardRes awardToUser(@RequestBody AwardReq req) {
        String reqJson = JSON.toJSONString(req);

        // 按照不同类型方法商品[1优惠券、2实物商品、3第三方兑换卡(爱奇艺)]
        logger.info("奖品发放开始[{}]req:{}", req.getUId(), reqJson);
        try {
            if (req.getAwardType() == 1) {
                CouponResult couponResult = couponInfo.sendCoupon(req.getUId(), req.getAwardNumber(), req.getBizId());
                logger.info(couponResult.toString());
                if (couponResult.getCode().equals("0000")) {
                    return AwardRes.builder().code("0000").info(req.getAwardType() + "-发放成功").build();
                } else {
                    return AwardRes.builder().code("0000").info(req.getAwardType() + "-发放失败").build();
                }
            } else if (req.getAwardType() == 2) {
                Boolean isSuccess = goodsInfo.deliverGoods(
                        DeliverReq.builder()
                                .userName(queryUserName(req.getUId()))
                                .userPhone(queryUserPhoneNumber(req.getUId()))
                                .sku(req.getAwardNumber())
                                .orderId(req.getBizId())
                                .consigneeUserName(req.getExtMap().get("consigneeUserName"))
                                .consigneeUserPhone(req.getExtMap().get("consigneeUserPhone"))
                                .consigneeUserName(req.getExtMap().get("consigneeUserAddress"))
                                .build());
                if (isSuccess) {
                    return AwardRes.builder().code("0000").info(req.getAwardType() + "-发放成功").build();
                } else {
                    return AwardRes.builder().code("9999").info(req.getAwardType() + "-发放失败").build();
                }
            } else if (req.getAwardType() == 3) {
                String bindMobileNumber = queryUserPhoneNumber(req.getUId());
                iQiYiCard.getIQiYiCard(bindMobileNumber, req.getAwardNumber());
                return AwardRes.builder().code("0000").info(req.getAwardType() + "-发放成功").build();
            }

        } catch (Exception e) {
            logger.error("发生异常");
        }
        return AwardRes.builder().code("9999").info(req.getAwardType() + "-发放失败").build();
    }

    private String queryUserName(String uId) {
        return "花花";
    }

    private String queryUserPhoneNumber(String uId) {
        return "15200101232";
    }
}
