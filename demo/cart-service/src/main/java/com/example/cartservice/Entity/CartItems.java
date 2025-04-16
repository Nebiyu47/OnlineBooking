package com.example.cartservice.Entity;

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
       private Cart cart;
       private Long bookId;
       private String bookTittle;
       private Double price;
       private Integer quantity;
}
