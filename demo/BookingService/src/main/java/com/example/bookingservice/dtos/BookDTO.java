package com.example.bookingservice.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
@Data
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private Set<String> categories;
    private LocalDate publishedDate;
    private String publisher;
    private String coverImageUrl;
    private Double averageRating;
}
