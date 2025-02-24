package com.cryptal.trading.service;

import com.cryptal.trading.domain.OrderType;
import com.cryptal.trading.model.Coin;
import com.cryptal.trading.model.Order;
import com.cryptal.trading.model.OrderItem;
import com.cryptal.trading.model.User;
import com.cryptal.trading.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImp implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WalletService walletService;
    
    @Override
    public Order createOrder(User user, OrderItem orderItem, OrderType orderType) {
        return null;
    }

    @Override
    public Order getOrderById(Long orderId) {
        return null;
    }

    @Override
    public List<Order> getAllOrdersOfUser(Long userID, OrderType orderType, String assetSymbol) {
        return List.of();
    }

    @Override
    public Order processOrder(Coin coin, double quantity, OrderType orderType, User user) {
        return null;
    }
}
