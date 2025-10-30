package com.example.rabbitmq1.controller;

import com.example.rabbitmq1.entity.ProductMessage;
import com.example.rabbitmq1.repository.ProductMessageRepository;
import com.example.rabbitmq1.service.ProductMessageProducer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class ProductMessageRestController {

    private final ProductMessageProducer producer;
    private final ProductMessageRepository repository;

    public ProductMessageRestController(ProductMessageProducer producer, ProductMessageRepository repository) {
        this.producer = producer;
        this.repository = repository;
    }

    // 모든 메시지 조회
    @GetMapping
    public List<ProductMessage> getAllMessages() {
        return repository.findAll();
    }

    // 메시지 전송
    @PostMapping
    public ProductMessage sendMessage(@RequestBody ProductMessage message) {
        producer.sendMessage(message);
        return repository.save(message); // DB에도 저장
    }
}

