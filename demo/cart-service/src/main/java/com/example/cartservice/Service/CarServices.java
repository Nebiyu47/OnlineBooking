package com.example.cartservice.Service;

import com.example.cartservice.Client.BookServiceClient;
import com.example.cartservice.Dto.BookDto;
import com.example.cartservice.Entity.Cart;
import com.example.cartservice.Entity.CartItems;
import com.example.cartservice.Exceptions.BookNotFoundException;
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

  @Transactional
  public Cart getCartByUserId(Long userId) {
    return cartRepository.findByUserId(userId)
            .orElseGet(() -> {
              Cart newCart = new Cart();
              newCart.setUserId(userId);
              return cartRepository.save(newCart);
            });
  }

  @Transactional
  public Cart addToCart(Long userId, Long bookId, Integer quantity) {
    // Validate quantity
    if (quantity <= 0) {
      throw new IllegalArgumentException("Quantity must be positive");
    }

    // Get book details
    BookDto book = bookServiceClient.getBookById(bookId);
    if (book == null) {
      throw new BookNotFoundException("Book not found: " + bookId);
    }

    // Get or create cart
    Cart cart = getCartByUserId(userId);

    // Check if item already exists
    cart.getItems().stream()
            .filter(item -> item.getBookId().equals(bookId))
            .findFirst()
            .ifPresentOrElse(
                    existingItem -> {
                      // Update existing item
                      existingItem.setQuantity(existingItem.getQuantity() + quantity);
                    },
                    () -> {
                      // Add new item
                      CartItems newItem = new CartItems();
                      newItem.setBookId(book.getId());
                      newItem.setBookTitle(book.getTitle());
                      newItem.setPrice(book.getPrice());
                      newItem.setQuantity(quantity);
                      newItem.setCart(cart);  // Set the cart reference
                      cart.getItems().add(newItem);
                    }
            );

    Cart savedCart = cartRepository.save(cart);
    cartEventProducer.sendCartUpdatedEvent(userId);
    return savedCart;
  }

  @Transactional
  public void removeFromCart(Long userId, Long itemId) {
    Cart cart = getCartByUserId(userId);
    boolean removed = cart.getItems().removeIf(item -> {
      if (item.getId().equals(itemId)) {
        item.setCart(null);  // Important: break the relationship
        return true;
      }
      return false;
    });

    if (removed) {
      cartRepository.save(cart);
      cartEventProducer.sendCartUpdatedEvent(userId);
    }
  }

  @Transactional
  public void clearCart(Long userId) {
    Cart cart = getCartByUserId(userId);
    cart.getItems().forEach(item -> item.setCart(null));  // Clear relationships
    cart.getItems().clear();
    cartRepository.save(cart);
    cartEventProducer.sendCartUpdatedEvent(userId);
  }
}
