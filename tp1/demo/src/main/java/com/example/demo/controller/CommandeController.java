package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Client;
import com.example.demo.entity.Commande;
import com.example.demo.service.CommandeService;
import com.example.demo.service.LigneCommandeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private LigneCommandeService ligneCommandeService;

    // Removed createCommande as it is handled by ClientController
    // @PostMapping("/home")

    @PostMapping(value = "/commande", params = "id")
    public String selectCommande(@RequestParam Long id, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        session.setAttribute("currentCommandeId", id);
        return "redirect:/commande";
    }

    @GetMapping("/commande")
    public String viewCommande(HttpSession session, Model model) {
        Client client = (Client) session.getAttribute("user");
        if (client == null)
            return "redirect:/login";

        Long commandeId = (Long) session.getAttribute("currentCommandeId");
        if (commandeId == null)
            return "redirect:/home";

        Commande commande = commandeService.getCommandeById(commandeId);
        if (commande != null) {
            model.addAttribute("commande", commande);
            model.addAttribute("client", client);
            return "commande";
        }
        return "redirect:/home";
    }

    @PostMapping(value = "/commande", params = { "libelle", "quantite", "prixUnitaire" })
    public String addLine(@RequestParam String libelle, @RequestParam Integer quantite,
            @RequestParam Double prixUnitaire, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        Long commandeId = (Long) session.getAttribute("currentCommandeId");
        if (commandeId != null) {
            ligneCommandeService.addLine(commandeId, libelle, quantite, prixUnitaire);
        }
        return "redirect:/commande";
    }

    @PostMapping(value = "/commande", params = "ligneId")
    public String removeLine(@RequestParam Long ligneId, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        ligneCommandeService.removeLine(ligneId);
        return "redirect:/commande";
    }

    @Autowired
    private com.example.demo.service.OrderProducer orderProducer;

    @PostMapping(value = "/commande", params = "action=submit")
    public String submitCommande(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        Long commandeId = (Long) session.getAttribute("currentCommandeId");
        if (commandeId != null) {
            System.out.println("Commande " + commandeId + " soumise !");
            Commande commande = commandeService.getCommandeById(commandeId);
            if (commande != null) {
                for (com.example.demo.entity.LigneCommande ligne : commande.getLignes()) {
                    String message = ligne.getLibelle() + ":" + ligne.getQuantite();
                    orderProducer.sendOrder(message);
                }
            }
        }
        return "redirect:/commande";
    }
}
