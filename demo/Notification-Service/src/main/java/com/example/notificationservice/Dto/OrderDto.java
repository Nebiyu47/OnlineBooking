package com.example.notificationservice.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {

    private Long id;               // Order ID for reference
    private Long userId;          // User to notify
    private LocalDateTime orderDate;  // When order was placed
    private String status;        // Order status (e.g., "PENDING")
    private Double totalAmount;
}
