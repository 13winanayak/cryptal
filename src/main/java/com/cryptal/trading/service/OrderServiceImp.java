package com.cryptal.trading.service;

import com.cryptal.trading.domain.OrderStatus;
import com.cryptal.trading.domain.OrderType;
import com.cryptal.trading.model.Coin;
import com.cryptal.trading.model.Order;
import com.cryptal.trading.model.OrderItem;
import com.cryptal.trading.model.User;
import com.cryptal.trading.repository.OrderItemRepository;
import com.cryptal.trading.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WalletService walletService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public Order createOrder(User user, OrderItem orderItem, OrderType orderType) {
        double price = orderItem.getCoin().getCurrentPrice() * orderItem.getQuantity();

        Order order = new Order();
        order.setUser(user);
        order.setOrderItem(orderItem);
        order.setOrderType(orderType);
        order.setPrice(BigDecimal.valueOf(price));
        order.setTimestamp(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);

        return orderRepository.save(order);
    }


    @Override
    public Order getOrderById(Long orderId) throws Exception {
        return orderRepository.findById(orderId)
                .orElseThrow(
                        () -> new Exception("order not found"));
    }


    @Override
    public List<Order> getAllOrdersOfUser(Long userID, OrderType orderType, String assetSymbol) {
        return orderRepository.findByUserId(userID);
    }

    private OrderItem createOrderItem(Coin coin, double quantity,
                                      double buyPrice, double sellPrice) {
        OrderItem orderItem = new OrderItem();
        orderItem.setCoin(coin);
        orderItem.setQuantity(quantity);
        orderItem.setBuyPrice(buyPrice);
        orderItem.setSellPrice(sellPrice);
        return orderItemRepository.save(orderItem);
    }

    @Transactional
    public Order buyAsset(Coin coin, double quantity, User user) throws Exception {
        if (quantity <= 0) {
            throw new Exception("quantity should be >0");
        }

        double buyPrice = coin.getCurrentPrice();

        OrderItem orderItem = createOrderItem(coin, quantity, buyPrice, 0);

        Order order = createOrder(user, orderItem, OrderType.BUY);
        orderItem.setOrder(order);

        walletService.payOrderPayment(order, user);

        order.setStatus(OrderStatus.SUCCESS);
        order.setOrderType(OrderType.BUY);
        Order savedOrder = orderRepository.save(order);

// create asset

        return savedOrder;

                        }
    


@Transactional
public Order sellAsset(Coin coin, double quantity, User user) throws Exception {
    if (quantity <= 0) {
        throw new Exception("quantity should be >0");
    }

    double buyPrice = coin.getCurrentPrice();

    OrderItem orderItem = createOrderItem(coin, quantity, buyPrice, 0);

    Order order = createOrder(user, orderItem, OrderType.BUY);
    orderItem.setOrder(order);

    walletService.payOrderPayment(order, user);

    order.setStatus(OrderStatus.SUCCESS);
    order.setOrderType(OrderType.BUY);
    Order savedOrder = orderRepository.save(order);

// create asset

    return savedOrder;

}



    @Override
    public Order processOrder(Coin coin, double quantity, OrderType orderType, User user) {
        return null;
    }
}
