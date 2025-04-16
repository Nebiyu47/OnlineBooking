package com.example.bookingservice.Entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Author is required")
    @Column(nullable = false)
    private String author;

    @Positive(message = "Price must be positive")
    @Column(nullable = false)
    private Double price;

    @PositiveOrZero
    @Column(nullable = false)
    private Integer stock = 0;  // Default value

    private String category;

    @Lob  // For large text
    private String description;

    @Column(name = "image_url")
    private String imageUrl;
}