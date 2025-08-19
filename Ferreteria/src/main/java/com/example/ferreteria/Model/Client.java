package com.example.ferreteria.Model;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long client_id;
    private String name;
    private String phone;
    private String address;
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Purchase_orders> orders = new ArrayList<>();

    public void agregarOrder(Purchase_orders order) {
        this.orders.add(order);
        order.setClient(this);
    }

    public int getTotalOrders() {
        return this.orders.size();
    }

    public BigDecimal getTotalGastado() {
        return this.orders.stream()
                .map(Purchase_orders::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
