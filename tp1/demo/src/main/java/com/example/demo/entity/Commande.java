package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
    List<LigneCommande> lignes;

    public Commande() {
    }

    public Commande(String titre, Client client) {
        this.titre = titre;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<LigneCommande> getLignes() {
        return lignes;
    }

    public void setLignes(List<LigneCommande> lignes) {
        this.lignes = lignes;
    }

    public Double getTotal() {
        if (lignes == null || lignes.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (LigneCommande ligne : lignes) {
            sum += ligne.getPrixTotal();
        }
        return sum;
    }
}
