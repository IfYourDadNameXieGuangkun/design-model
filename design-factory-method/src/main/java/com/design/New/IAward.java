package com.design.New;

import com.design.dto.AwardReq;
import com.design.dto.AwardRes;

//奖品发放接口
public interface IAward {

    public AwardRes sendAward(AwardReq awardReq);

}
