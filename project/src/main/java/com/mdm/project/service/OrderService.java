package com.mdm.project.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdm.project.dto.OrderDetailsDto;
import com.mdm.project.dto.ProductDetailsDto;
import com.mdm.project.entity.AddressEntity;
import com.mdm.project.entity.CartItem;
import com.mdm.project.entity.OrderTable;
import com.mdm.project.entity.ShipMethod;
import com.mdm.project.exception.ResourceNotFoundException;
import com.mdm.project.repository.OrderTableRepository;
import com.mdm.project.request.OrderRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
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
        order.setShopName(request.getShopName());

        double totalPrice = 0;
        for (CartItem item : request.getProducts()) {
            totalPrice += (item.getPrice() * item.getQuantity());
        }

        order.setTotal(totalPrice);


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

    public List<OrderDetailsDto> getOrderDetailsByUserId(String userId) {
        List<OrderTable> userOrders = orderRepository.findByKeyUserId(userId).orElseThrow(() -> new ResourceNotFoundException("Order", "User id", userId));


        List<OrderDetailsDto> orderDetailsList = userOrders.stream().map((item) -> {
            OrderDetailsDto orderDetailsDto = new OrderDetailsDto();
            try {
                List<CartItem> products = objectMapper.readValue(item.getProducts(), new TypeReference<>() {
                });
                ShipMethod shipMethod = objectMapper.readValue(item.getShipMethod(), new TypeReference<>() {});
                AddressEntity address = objectMapper.readValue(item.getAddress(), new TypeReference<>() {});

                orderDetailsDto.setId(item.getOrderId());
                orderDetailsDto.setUserId(item.getKey().getUserId());
                orderDetailsDto.setOrderTime(item.getKey().getOrderTime().toString());
                orderDetailsDto.setProducts(products);
                orderDetailsDto.setStatus(item.getStatus());
                orderDetailsDto.setShipMethod(shipMethod);
                orderDetailsDto.setShipAddress(address);
                orderDetailsDto.setPaymentMethod(item.getPaymentMethod());
                orderDetailsDto.setShopName(item.getShopName());
                orderDetailsDto.setTotalPrice(item.getTotal());

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return orderDetailsDto;
        }).toList();

        return orderDetailsList;
    }

}
