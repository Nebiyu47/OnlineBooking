package com.example.cartservice.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CartItems {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @ManyToOne(fetch = FetchType.LAZY)
       @JoinColumn(name = "cart_id")
       @JsonBackReference
       private Cart cart;

       private Long bookId;
       private String bookTitle;  // Fixed typo from bookTittle
       private Double price;
       private Integer quantity;

       @PrePersist
       @PreUpdate
       private void validate() {
              if (quantity <= 0) {
                     throw new IllegalArgumentException("Quantity must be positive");
              }
       }
}
