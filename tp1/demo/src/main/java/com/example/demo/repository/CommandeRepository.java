package com.example.demo.repository;

import com.example.demo.entity.Client;
import com.example.demo.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findByClient(Client client);
}
