package com.example.orderservice.Dto;

import lombok.Data;

@Data
public class CartItemDto {
   private Long id;
   private Long nookId;
   private String bookTittle;
   private Double price;
   private Integer quantity;
}
