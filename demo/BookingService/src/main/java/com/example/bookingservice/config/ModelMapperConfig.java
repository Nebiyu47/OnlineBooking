package com.example.bookingservice.config;

import com.example.bookingservice.Entites.Book;
import com.example.bookingservice.dtos.BookDTO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper= new ModelMapper();
        modelMapper.typeMap(Book.class, BookDTO.class)
                .addMapping(mapper -> {
                    mapper.map(src->src.)
                })
    }
}
