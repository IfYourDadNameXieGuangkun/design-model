package com.design.New;


public class AwardFactory {

    public IAward createAward(int type){
       return type==1?new CouponAward():(type==2?new GoodsAward():new CardAward());
    }
}
