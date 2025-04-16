package com.example.bookingservice.exceptions;

public class DuplicatedIsbnException extends RuntimeException{
    public DuplicatedIsbnException(String isbn){
        super("Book with Isbn"+ isbn +"already exists");
    }
}
