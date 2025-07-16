package com.orderservice.order.controller;

import com.orderservice.order.dto.CreateOrderRequest;
import com.orderservice.order.dto.OrderResponse;
import com.orderservice.order.model.OrderMessage;
import com.orderservice.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public OrderResponse create(@RequestBody CreateOrderRequest request) {
        return service.createOrder(request);
    }

    @GetMapping("/{id}")
    public OrderMessage getById(@PathVariable String id) {
        return service.getOrderById(id);
    }
}
