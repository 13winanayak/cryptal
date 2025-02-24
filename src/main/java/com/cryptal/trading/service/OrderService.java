package com.cryptal.trading.service;

import com.cryptal.trading.domain.OrderType;
import com.cryptal.trading.model.Coin;
import com.cryptal.trading.model.Order;
import com.cryptal.trading.model.OrderItem;
import com.cryptal.trading.model.User;

import java.util.List;

public interface OrderService {

    Order createOrder(User user, OrderItem orderItem, OrderType orderType);

    Order getOrderById(Long orderId);

    List<Order> getAllOrdersOfUser(Long userID, OrderType orderType, String assetSymbol);

    Order processOrder(Coin coin, double quantity, OrderType orderType, User user);

}
