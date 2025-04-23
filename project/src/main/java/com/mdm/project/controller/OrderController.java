package com.mdm.project.controller;


import com.mdm.project.dto.OrderDetailsDto;
import com.mdm.project.request.OrderRequest;
import com.mdm.project.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest order) {
        orderService.createOrder(order);
        return ResponseEntity.ok("Order created");
    }

    @GetMapping("/get-orders/{userId}")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable String userId) {
        List<OrderDetailsDto> orderDetailsDtoList = orderService.getOrderDetailsByUserId(userId);

        return ResponseEntity.ok(orderDetailsDtoList);
    }
}
