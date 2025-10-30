package com.example.rabbitmq1.service;

import com.example.rabbitmq1.config.RabbitConfig;
import com.example.rabbitmq1.entity.ProductMessage;
import com.example.rabbitmq1.repository.ProductMessageRepository;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ProductMessageConsumer {

    private final ProductMessageRepository repository;
    private final Gson gson = new Gson();

    public ProductMessageConsumer(ProductMessageRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME2)
    public void receiveMessage(String jsonMessage) {
        ProductMessage message = gson.fromJson(jsonMessage, ProductMessage.class);
        repository.save(message);
        System.out.println("Saved message: " + message.getTitle());
    }
}

