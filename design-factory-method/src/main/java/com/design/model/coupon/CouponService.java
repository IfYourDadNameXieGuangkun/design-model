package com.design.model.coupon;

import com.design.model.Aye;
import org.springframework.stereotype.Service;

/**
 * 优惠券服务
 */
@Service
@Aye
public class CouponService implements CouponInfo {

    @Override
    public CouponResult sendCoupon(String uId, String couponNumber, String uuid) {
        System.out.println("模拟发放优惠券一张：" + uId + "," + couponNumber + "," + uuid);
        return new CouponResult("0000", "发放成功");
    }

}
