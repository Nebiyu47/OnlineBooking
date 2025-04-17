package com.example.orderservice.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private Long userId;
   private LocalDateTime orderDate;
   private Double TotalAmount;
   private String status;
   @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,orphanRemoval = true)
   @JsonManagedReference
    private List<OrderItem> items= new ArrayList<>();
   @PrePersist
    protected void onCreate(){
       orderDate =LocalDateTime.now();
       status="PENDING";
   }

}
