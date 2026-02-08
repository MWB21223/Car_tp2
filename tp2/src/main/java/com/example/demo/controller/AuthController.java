package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping
    public String authPage() {
        return "auth";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        System.out.println("Login attempt: " + email);
        // Placeholder logic
        return "redirect:/home";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String email, @RequestParam String firstname, @RequestParam String lastname,
            @RequestParam String password) {
        System.out.println("Signup attempt: " + email);
        // Placeholder logic
        return "redirect:/home";
    }
}
