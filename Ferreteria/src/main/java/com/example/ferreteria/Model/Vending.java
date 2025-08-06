package com.example.ferreteria.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "vending")

public class Vending {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long vending_id;


}
