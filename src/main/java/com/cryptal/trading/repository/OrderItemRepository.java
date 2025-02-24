package com.cryptal.trading.repository;

import com.cryptal.trading.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository <OrderItem, Long>{
}
