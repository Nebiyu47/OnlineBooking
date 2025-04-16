package com.example.notificationservice.Service;

import com.example.notificationservice.Entity.Notification;
import com.example.notificationservice.Repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    public Notification createNotification(Long userId, String title , String message, String type){
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setType(type);
        return notificationRepository.save(notification);
    }
    public List<Notification> getNotificationByUserId(Long userId){
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }
    public void markAsRead(Long notificationId){
        notificationRepository.findById(notificationId).ifPresent(notfication->{
            notfication.setRead(true);
            notificationRepository.save(notfication);
        });
    }

}
