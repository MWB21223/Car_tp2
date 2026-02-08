package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    @Autowired
    private StockService stockService;

    @KafkaListener(topics = "orders", groupId = "stock-group")
    public void consumeOrder(String message) {
        System.out.println("Received Message: " + message);
        stockService.processOrder(message);
    }
}
