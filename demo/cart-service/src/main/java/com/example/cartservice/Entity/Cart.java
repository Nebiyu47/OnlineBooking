package com.example.cartservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="carts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CartItems> items= new ArrayList<>();
    public void addItem(CartItems item){
        items.add(item);
        item.setCart(this);

    }
    public void removeItem(CartItems item){
        items.remove(item);
        item.setCart(null);
    }
    public Double getTotal(){
        return items.stream().mapToDouble(item ->item.getPrice()* item.getQuantity())
                .sum();

    }
}
