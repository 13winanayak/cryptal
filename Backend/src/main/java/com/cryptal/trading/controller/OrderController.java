package com.cryptal.trading.controller;

import com.cryptal.trading.domain.OrderType;
import com.cryptal.trading.exception.UserException;
import com.cryptal.trading.model.Coin;
import com.cryptal.trading.model.Order;
import com.cryptal.trading.service.WalletTransactionService;
import com.cryptal.trading.model.User;
import com.cryptal.trading.request.CreatOrderReqest;
import com.cryptal.trading.service.CoinService;
import com.cryptal.trading.service.OrderService;
import com.cryptal.trading.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;

    private UserService userSerivce;

    @Autowired
    private CoinService coinService;

    @Autowired
    private WalletTransactionService walletTransactionService;

//    private

    @Autowired
    public OrderController(OrderService orderService, UserService userSerivce) {
        this.orderService = orderService;
        this.userSerivce=userSerivce;
    }

    @PostMapping("/pay")
    public ResponseEntity<Order> payOrderPayment(
            @RequestHeader("Authorization") String jwt,
            @RequestBody CreatOrderReqest req

    ) throws Exception, UserException {
        User user = userSerivce.findUserProfileByJwt(jwt);
        Coin coin =coinService.findById(req.getCoinId());


        Order order = orderService.processOrder(coin,req.getQuantity(),req.getOrderType(),user);

        return ResponseEntity.ok(order);

    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable Long orderId
    ) throws Exception, UserException {
        if (jwtToken == null) {
            throw new Exception("token missing...");
        }

        User user = userSerivce.findUserProfileByJwt(jwtToken);

        Order order = orderService.getOrderById(orderId);
        if (order.getUser().getId().equals(user.getId())) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<Order>> getAllOrdersForUser(
            @RequestHeader("Authorization") String jwtToken,
            @RequestParam(required = false) String order_type,
            @RequestParam(required = false) String asset_symbol
    ) throws Exception, UserException {
        if (jwtToken == null) {
            throw new Exception("token missing...");
        }

        Long userId = userSerivce.findUserProfileByJwt(jwtToken).getId();

        List<Order> userOrders = orderService.getAllOrdersForUser(userId,order_type,asset_symbol);
        return ResponseEntity.ok(userOrders);
    }



}
