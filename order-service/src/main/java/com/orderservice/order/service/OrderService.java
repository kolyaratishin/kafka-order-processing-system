package com.orderservice.order.service;

import com.orderservice.order.dto.CreateOrderRequest;
import com.orderservice.order.dto.OrderResponse;
import com.orderservice.order.kafka.OrderProducer;
import com.orderservice.order.model.OrderMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ConcurrentHashMap<String, OrderMessage> store = new ConcurrentHashMap<>();
    private final OrderProducer orderProducer;

    public OrderResponse createOrder(CreateOrderRequest request) {
        OrderMessage orderMessage = new OrderMessage(request.getProductId(), request.getQuantity());
        store.put(orderMessage.getId(), orderMessage);

        orderProducer.sendOrder(orderMessage); // <-- надсилаємо в Kafka

        return mapToResponse(orderMessage);
    }

    public OrderMessage getOrderById(String id) {
        return store.get(id);
    }

    private OrderResponse mapToResponse(OrderMessage orderMessage) {
        OrderResponse response = new OrderResponse();
        response.setId(orderMessage.getId());
        response.setProductId(orderMessage.getProductId());
        response.setQuantity(orderMessage.getQuantity());
        response.setStatus(orderMessage.getStatus());
        return response;
    }
}
