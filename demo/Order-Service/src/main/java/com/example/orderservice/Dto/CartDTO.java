package com.example.orderservice.Dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
 private Long id;
 private Long userId;
 private List<CartItemDto> items;
 private Double total;

}
