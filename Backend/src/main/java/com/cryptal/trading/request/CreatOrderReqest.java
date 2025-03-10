package com.cryptal.trading.request;

import com.cryptal.trading.domain.OrderType;
import lombok.Data;

@Data
public class CreatOrderReqest {

    private String coinId;
    private double quantity;
    private OrderType orderType;

    public String getCoinId() {
        return coinId;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }
}
