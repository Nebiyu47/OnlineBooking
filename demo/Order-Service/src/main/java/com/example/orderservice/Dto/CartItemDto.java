package com.example.orderservice.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CartItemDto {
   private Long id;
   private Long bookId;
   private String bookTitle;  // Fixed typo from bookTittle to bookTitle
   private Double price;
   private Integer quantity;

   // Optional: Add validation annotations

}