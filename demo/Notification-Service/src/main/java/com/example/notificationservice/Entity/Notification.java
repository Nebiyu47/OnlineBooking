package com.example.notificationservice.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Data
public class Notification {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Long userId;
        private String title;
        private String message;
        private String type;
        private LocalDateTime createdAt;
        @Column(name = "is_read")
        private boolean read;

   @PrePersist
    protected void onCreate(){
       createdAt=LocalDateTime.now();
       read=false;
   }
}
