package com.example.demo.controller;

import com.example.demo.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private StockService stockService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("stock", stockService.getStock());
        return "home";
    }

    @PostMapping("/restock")
    public String restock() {
        stockService.restock();
        return "redirect:/home";
    }
}
