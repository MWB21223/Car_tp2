package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.service.ClientService;
import com.example.demo.service.CommandeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CommandeService commandeService;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("client", new Client());
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(@ModelAttribute Client client) {
        clientService.register(client);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("client", new Client()); 
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email, @RequestParam String password, HttpSession session,
            Model model) {
        Client client = clientService.authenticate(email, password);
        if (client != null) {
            session.setAttribute("user", client);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        Client client = (Client) session.getAttribute("user");
        if (client != null) {
            model.addAttribute("client", client);
            model.addAttribute("commandes", commandeService.findCommandesByClient(client));
            return "index";
        }
        return "redirect:/login";
    }

    @PostMapping("/home")
    public String createCommande(@RequestParam String titre, HttpSession session) {
        Client client = (Client) session.getAttribute("user");
        if (client != null) {
            commandeService.createCommande(titre, client);
            return "redirect:/home";
        }
        return "redirect:/login";
    }

}
