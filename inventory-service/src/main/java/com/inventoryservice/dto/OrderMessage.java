package com.inventoryservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderMessage {
    private String id;
    private String productId;
    private int quantity;
    private OrderStatus status;
}
