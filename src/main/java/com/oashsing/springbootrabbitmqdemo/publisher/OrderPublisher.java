package com.oashsing.springbootrabbitmqdemo.publisher;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oashsing.springbootrabbitmqdemo.config.MessagingConfig;
import com.oashsing.springbootrabbitmqdemo.entity.Order;
import com.oashsing.springbootrabbitmqdemo.entity.OrderStatus;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {

        order.setOrderId(UUID.randomUUID().toString());

        // restaurant service
        // payment service
        OrderStatus orderStatus = new OrderStatus(order,
                "PROGRESS",
                "ORDER PLACED SUCCESSFULLY");
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE,
                MessagingConfig.KEY,
                orderStatus);
        return "Success!!";
    }
}
