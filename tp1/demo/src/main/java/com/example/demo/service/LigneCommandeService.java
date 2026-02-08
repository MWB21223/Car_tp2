package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Commande;
import com.example.demo.entity.LigneCommande;
import com.example.demo.repository.CommandeRepository;
import com.example.demo.repository.LigneCommandeRepository;

@Service
public class LigneCommandeService {

    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    @Autowired
    private CommandeRepository commandeRepository;

    public void addLine(Long commandeId, String libelle, Integer quantite, Double prixUnitaire) {
        Optional<Commande> commandeOpt = commandeRepository.findById(commandeId);
        if (commandeOpt.isPresent()) {
            Commande commande = commandeOpt.get();
            LigneCommande ligne = new LigneCommande(libelle, quantite, prixUnitaire, commande);
            ligneCommandeRepository.save(ligne);
        }
    }

    public void removeLine(Long ligneId) {
        ligneCommandeRepository.deleteById(ligneId);
    }
}
