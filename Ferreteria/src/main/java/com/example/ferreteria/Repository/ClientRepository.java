package com.example.ferreteria.Repository;

import com.example.ferreteria.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
