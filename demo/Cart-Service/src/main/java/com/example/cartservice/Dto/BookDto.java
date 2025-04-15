package com.example.cartservice.Dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class BookDto {

    private Long Id;

    private String title;

    private String author;

    private Double price;

    private Integer stock;
    private String category;
    private String description;
    private String imageUrl;
}
