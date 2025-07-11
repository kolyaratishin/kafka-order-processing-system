package com.orderservice.order.service;

import com.orderservice.order.dto.CreateOrderRequest;
import com.orderservice.order.dto.OrderResponse;
import com.orderservice.order.kafka.OrderProducer;
import com.orderservice.order.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ConcurrentHashMap<String, Order> store = new ConcurrentHashMap<>();
    private final OrderProducer orderProducer;

    public OrderResponse createOrder(CreateOrderRequest request) {
        Order order = new Order(request.getProductId(), request.getQuantity());
        store.put(order.getId(), order);

        orderProducer.sendOrder(order); // <-- надсилаємо в Kafka

        return mapToResponse(order);
    }

    public Order getOrderById(String id) {
        return store.get(id);
    }

    private OrderResponse mapToResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setProductId(order.getProductId());
        response.setQuantity(order.getQuantity());
        response.setStatus(order.getStatus());
        return response;
    }
}
