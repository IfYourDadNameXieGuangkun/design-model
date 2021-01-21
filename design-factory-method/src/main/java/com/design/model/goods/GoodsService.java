package com.design.model.goods;

import com.alibaba.fastjson.JSON;
import com.design.model.Aye;
import org.springframework.stereotype.Service;

/**
 * 实物商品服务
 */
@Service
@Aye
public class GoodsService implements GoodsInfo  {

    @Override
    public Boolean deliverGoods(DeliverReq req) {
        System.out.println("模拟发货实物商品一个：" + JSON.toJSONString(req));
        return true;
    }

}
