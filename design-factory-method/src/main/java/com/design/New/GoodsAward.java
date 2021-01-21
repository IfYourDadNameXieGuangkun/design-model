package com.design.New;

import com.design.dto.AwardReq;
import com.design.dto.AwardRes;
import com.design.model.goods.DeliverReq;
import com.design.model.goods.GoodsInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsAward implements IAward {
    private Logger logger = LoggerFactory.getLogger(this.getClass());;
    @Autowired
    private GoodsInfo goodsInfo;
    @Override
    public AwardRes sendAward(AwardReq req) {
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
    }

    private String queryUserName(String uId) {
        return "花花";
    }

    private String queryUserPhoneNumber(String uId) {
        return "15200101232";
    }

}
