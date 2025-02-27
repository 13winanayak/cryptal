package com.cryptal.trading.request;

import com.cryptal.trading.domain.OrderType;
import lombok.Data;

@Data
public class CreatOrderReqest {

    private String coinId;
    private double quantity;
    private OrderType orderType;
    
}
