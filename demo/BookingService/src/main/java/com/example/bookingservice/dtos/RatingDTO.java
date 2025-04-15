package com.example.bookingservice.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class RatingDTO {
    private Long id;
    @Min(1) @Max(5)
    private Integer value;
    private String review;
    private Long userId;
    private String userName;
    private LocalDateTime createdAt;
    // Getters and setters
}
