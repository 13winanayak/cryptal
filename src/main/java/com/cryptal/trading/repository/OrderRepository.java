package com.cryptal.trading.repository;

import com.cryptal.trading.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByUserId(Long userId);
}
