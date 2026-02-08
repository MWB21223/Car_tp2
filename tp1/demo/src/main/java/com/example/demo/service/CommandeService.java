package com.example.demo.service;

import com.example.demo.entity.Client;
import com.example.demo.entity.Commande;
import com.example.demo.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    public Commande createCommande(String titre, Client client) {
        Commande commande = new Commande(titre, client);
        return commandeRepository.save(commande);
    }

    public List<Commande> findCommandesByClient(Client client) {
        return commandeRepository.findByClient(client);
    }

    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id).orElse(null);
    }
}
