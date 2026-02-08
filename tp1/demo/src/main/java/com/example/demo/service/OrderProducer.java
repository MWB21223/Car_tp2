package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private static final String TOPIC = "orders";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendOrder(String message) {
        System.out.println("Sending message to topic " + TOPIC + ": " + message);
        kafkaTemplate.send(TOPIC, message);
    }
}
