package com.example.demo.service;

import com.example.demo.entity.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client register(Client client) {
        // In a real app, hash the password here
        return clientRepository.save(client);
    }

    public Client authenticate(String email, String password) {
        Optional<Client> client = clientRepository.findByEmail(email);
        if (client.isPresent() && client.get().getPassword().equals(password)) {
            return client.get();
        }
        return null; // Or throw exception
    }
}
