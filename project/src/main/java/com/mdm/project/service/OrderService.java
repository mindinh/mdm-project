package com.mdm.project.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdm.project.entity.OrderTable;
import com.mdm.project.repository.OrderTableRepository;
import com.mdm.project.request.OrderRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderTableRepository orderRepository;
    private final ObjectMapper objectMapper;
    private final RedisIdGenerator idGenerator;
    public OrderService(OrderTableRepository orderRepository, ObjectMapper objectMapper, RedisIdGenerator idGenerator) {
        this.orderRepository = orderRepository;
        this.objectMapper = objectMapper;
        this.idGenerator = idGenerator;
    }

    public void createOrder(OrderRequest request) {
        OrderTable order = new OrderTable();
        OrderTable.OrderKey key = new OrderTable.OrderKey();
        key.setUserId(request.getUserId());
        key.setOrderTime(Instant.now());

        order.setKey(key);
//        order.setOrderId(UUID.randomUUID().toString());
        order.setOrderId(idGenerator.getNextIdWithPrefix("order", "O"));
        order.setStatus("PENDING");
        order.setPaymentMethod(request.getPaymentMethod());
        order.setTotal(request.getTotalPrice());
        try {
            String productJson = objectMapper.writeValueAsString(request.getProducts());
            String addressJson = objectMapper.writeValueAsString(request.getAddress());
            String shipMethodJson = objectMapper.writeValueAsString(request.getShipMethod());

            order.setProducts(productJson);
            order.setAddress(addressJson);
            order.setShipMethod(shipMethodJson);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize product/address", e);
        }

        orderRepository.save(order);
    }

}
