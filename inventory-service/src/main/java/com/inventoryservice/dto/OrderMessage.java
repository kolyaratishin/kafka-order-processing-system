package com.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderMessage {
    private String id;
    private String productId;
    private int quantity;
    private OrderStatus status;
}
