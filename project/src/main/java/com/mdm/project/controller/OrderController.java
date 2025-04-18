package com.mdm.project.controller;


import com.mdm.project.request.OrderRequest;
import com.mdm.project.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
