package com.example.Homework_3.service;

import com.example.Homework_3.domain.Client;
import com.example.Homework_3.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }
}
