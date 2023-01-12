package com.oashsing.springbootrabbitmqdemo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.oashsing.springbootrabbitmqdemo.config.MessagingConfig;
import com.oashsing.springbootrabbitmqdemo.entity.OrderStatus;

@Component
public class User {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    void consumeMessageFromQueue(OrderStatus orderStatus) {
        System.out.println(orderStatus);
    }
}
