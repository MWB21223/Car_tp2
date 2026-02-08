package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class LigneCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;
    private Integer quantite;
    private Double prixUnitaire;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;

    public LigneCommande() {
    }

    public LigneCommande(String libelle, Integer quantite, Double prixUnitaire, Commande commande) {
        this.libelle = libelle;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.commande = commande;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Double getPrixTotal() {
        if (quantite != null && prixUnitaire != null) {
            return quantite * prixUnitaire;
        }
        return 0.0;
    }
}
