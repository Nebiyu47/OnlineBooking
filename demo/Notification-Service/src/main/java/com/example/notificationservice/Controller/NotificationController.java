package com.example.notificationservice.Controller;

import com.example.notificationservice.Entity.Notification;
import com.example.notificationservice.Service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable Long userId){
        return ResponseEntity.ok(notificationService.getNotificationByUserId(userId));
    }
    @PostMapping("/{notificationId}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long notificationId){
        notificationService.markAsRead(notificationId);
        return ResponseEntity.noContent().build();
    }

}
