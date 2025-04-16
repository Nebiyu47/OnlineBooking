package com.example.cartservice.Service;

import com.example.cartservice.Client.BookServiceClient;
import com.example.cartservice.Dto.BookDto;
import com.example.cartservice.Entity.Cart;
import com.example.cartservice.Entity.CartItems;
import com.example.cartservice.Repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;


@Service
@RequiredArgsConstructor
public class CarServices {
  private final CartRepository cartRepository;
  private final BookServiceClient bookServiceClient;
  private final CartEventProducer cartEventProducer;
  public Cart getCartByUserId(Long userId){
    return cartRepository.findById(userId).orElseGet(()->{

      Cart newCart= new Cart();
      newCart.setUserId(userId);
      return cartRepository.save(newCart);
    });
  }
  @Transactional
  public Cart addToCart(Long userId, Long bookId, Integer quantity){
    BookDto book = bookServiceClient.getBookById(bookId);
    Cart cart = getCartByUserId(userId);
    CartItems item = new CartItems();
    item.setBookId(book.getId());
    item.setBookTittle(book.getTitle());
    item.setPrice(book.getPrice());
    item.setQuantity(quantity);
    cart.addItem(item);
    Cart savedCart = cartRepository.save(cart);
    cartEventProducer.sendCartUpdatedEvent(userId);
    return savedCart;
  }
  @Transactional
  public void removeFromCart(Long userId,Long itemId){
    Cart cart= getCartByUserId(userId);
    cart.getItems().removeIf(items-> items.getId().equals(itemId));
    cartRepository.save(cart);
    cartEventProducer.sendCartUpdatedEvent(userId);
  }
  @Transactional
  public void clearCart(Long userId){
    Cart cart = getCartByUserId(userId);
    cart.getItems().clear();
    cartRepository.save(cart);
  }
}
