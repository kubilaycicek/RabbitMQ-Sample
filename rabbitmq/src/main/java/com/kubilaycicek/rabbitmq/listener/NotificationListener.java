package com.kubilaycicek.rabbitmq.listener;

import com.kubilaycicek.rabbitmq.model.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    @RabbitListener
    public void handleMessage(Notification notification) {
        System.out.println(notification.toString());
    }
}
