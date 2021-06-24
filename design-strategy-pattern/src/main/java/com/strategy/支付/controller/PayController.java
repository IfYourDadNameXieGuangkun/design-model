package com.strategy.支付.controller;

import com.strategy.支付.service.IPay;
import com.strategy.支付.common.PayChannel;
import com.strategy.支付.common.PayStrategy;
import exception.aye.model.dto.CR;
import exception.aye.model.dto.ControllerResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
@Slf4j
public class PayController extends PayStrategy {

    @RequestMapping("/{channel}")
    public CR<?> pay(@PathVariable String channel, String orderCode) {
        return ControllerResultDto.create(getPayAdapter(channel).pay(PayChannel.getPayChannelByChannel(channel), orderCode));
    }
}
