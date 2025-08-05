package com.example.ferreteria.controllers;

import com.example.ferreteria.Repository.ClientRepository;
import com.example.ferreteria.Model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
   private ClientRepository clientRepository;

    @GetMapping
    public List<Client> getAll()
    {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable Long id)
    {
        return clientRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Client create(@RequestBody Client client)
    {
        return clientRepository.save(client);
    }

    @PostMapping("/{id}")
    public Client update(@PathVariable Long id, @RequestBody Client client)
    {
        client.setClient_id(id);
        return clientRepository.save(client);
    }

    @DeleteMapping
    public void delete(@PathVariable Long id)
    {
        clientRepository.deleteById(id);
    }
}
