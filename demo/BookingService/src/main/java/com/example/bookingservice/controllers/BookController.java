package com.example.bookingservice.controllers;

import com.example.bookingservice.Entites.Book;
import com.example.bookingservice.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping("/id")
    public ResponseEntity<Book> getBookId(@PathVariable Long id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }
    @PostMapping
    public Book creatBook(@RequestBody Book book){
        return bookService.createBook(book);
    }
    @PutMapping
    public ResponseEntity<Book> updateBook (@PathVariable Long id , @RequestBody Book bookdetails){
        return  ResponseEntity.ok(bookService.updateBook(id,bookdetails));
    }
    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteBook (@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public List<Book> searchBook(@RequestParam String query){
        return bookService.searchBook(query);
    }
}
