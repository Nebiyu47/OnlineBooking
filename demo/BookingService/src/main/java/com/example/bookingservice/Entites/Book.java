package com.example.bookingservice.Entites;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false)
    private String titile;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false , unique = true)
    private String isbn;
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    @Column (nullable = false)
    private Integer quantity;

    @ElementCollection
    private Set<String> categories= new HashSet<>();
    private LocalDate publishedDate;
    private String publisher;
    private String convertImageUrl;
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();
    @Column(precision = 2 , scale = 1)
    private Double averageRating =0.0;
    @CreationTimestamp
    private LocalDateTime updateAt;



}
