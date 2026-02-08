package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class StockService {
    private Map<String, Integer> stock;

    public StockService() {
        stock = new HashMap<>();
        stock.put("Sneakers", 10);
        stock.put("Pantalon", 5);
        stock.put("Pull", 8);
    }

    public Map<String, Integer> getStock() {
        return stock;
    }

    public void restock() {
        stock.put("Sneakers", 20);
        stock.put("Pantalon", 20);
        stock.put("Pull", 20);
    }

    public void processOrder(String orderData) {
        try {
            String[] parts = orderData.split(":");
            if (parts.length == 2) {
                String article = parts[0];
                int quantity = Integer.parseInt(parts[1]);
                stock.computeIfPresent(article, (k, v) -> Math.max(0, v - quantity));
                System.out.println("Stock updated: " + article + " -" + quantity);
            } else {
                System.err.println("Invalid message format: " + orderData);
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid quantity in message: " + orderData);
        }
    }
}
