package com.example.bookingservice.Entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "book_id",nullable = false)
    private Book book;
//    @ManyToOne
//    @JoinColumn(name="user_id",nullable = false)
//    private  User
    @Column(nullable = false)
    @Min (1)
    @Max(5)
    private Integer value;
    private String review;
    @CreationTimestamp
    private LocalDateTime createdAt;

}
