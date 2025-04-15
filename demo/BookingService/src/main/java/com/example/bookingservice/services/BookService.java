package com.example.bookingservice.services;



import com.example.bookingservice.Entites.Book;
import com.example.bookingservice.exceptions.BookNotFoundException;
import com.example.bookingservice.repositories.BookRpositories;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class BookService {

   private final BookRpositories bookRpositories;
   public List<Book> getAllBooks(){
       return bookRpositories.findAll();
   }
   public Book getBookById(Long id){
       return bookRpositories.findById(id).orElseThrow(()-> new BookNotFoundException("Book not Found with id :0"+id));
   }
   public Book createBook(Book book){
       return bookRpositories.save(book);
   }
   public Book updateBook(Long id , Book bookDetails){
       Book book= getBookById(id);
       book.setTitle(bookDetails.getTitle());
       book.setAuthor(bookDetails.getAuthor());
       book.setPrice(bookDetails.getPrice());
       book.setStock(bookDetails.getStock());
       book.setCategory(bookDetails.getCategory());
       book.setDescription(bookDetails.getDescription());
       book.setImageUrl(bookDetails.getImageUrl());
       return bookRpositories.save(book);
   }
   public  void deleteBook(Long id){
       Book book = getBookById(id);
       bookRpositories.delete(book);
   }
   public List<Book> searchBook (String query){
       return bookRpositories.findByTitleContainingOrDescriptionContaining(query , query);
   }
   public void updateStock (Long bookId, int quantity){
       Book book = getBookById(bookId);
       if(book.getStock()<quantity){
           throw new RuntimeException("Insufficint stock");
       }
       book.setStock(book.getStock()-quantity);
       bookRpositories.save(book);
   }
}
