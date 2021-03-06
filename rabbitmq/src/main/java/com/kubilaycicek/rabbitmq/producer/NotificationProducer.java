package com.kubilaycicek.rabbitmq.producer;

import com.kubilaycicek.rabbitmq.model.Notification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.UUID;

@Service
public class NotificationProducer {
    @Value("${myapp.rabbit.routing.name}")
    private String routingName;

    @Value("${myapp.rabbit.exchange.name}")
    private String exchangeName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public  void init(){
        Notification notification = new Notification();
        notification.setNotificationId(UUID.randomUUID().toString());
        notification.setCreatedAt(new Date());
        notification.setMessage("Benden selam olsun Bolu Beyi !");
        notification.setSeen(false);

        sendToQueue(notification);
    }

    public void sendToQueue(Notification notification) {
        System.out.println("Notification Sent ID : " + notification.getNotificationId());
        rabbitTemplate.convertAndSend(exchangeName,routingName, notification);
    }
}
