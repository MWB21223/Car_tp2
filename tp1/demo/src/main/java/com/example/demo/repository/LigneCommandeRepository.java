package com.example.demo.repository;

import com.example.demo.entity.Commande;
import com.example.demo.entity.LigneCommande;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {
    List<LigneCommande> findByCommande(Commande commande);
}