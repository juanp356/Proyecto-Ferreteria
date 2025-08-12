package com.example.ferreteria.service;

import com.example.ferreteria.Repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {


    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public Long GetTotalCustomers() {
        return clientRepository.countClients();
    }

    public Long TotalCustomers(){
        return clientRepository.countClientsR();
    }



}
