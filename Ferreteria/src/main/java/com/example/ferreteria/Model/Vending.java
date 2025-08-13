package com.example.ferreteria.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "vending")

public class Vending {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Long vending_id;
        private int total_amount;
        private Date vending_date;
        @ManyToOne
        @JoinColumn(name = "client_id")
        private  Client client_id;
        @ManyToOne
        @JoinColumn(name = "employee_id")
        private Employee employee_id;

    public Long getVending_id() {
        return vending_id;
    }

    public void setVending_id(Long vending_id) {
        this.vending_id = vending_id;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public Date getVending_date() {
        return vending_date;
    }

    public void setVending_date(Date vending_date) {
        this.vending_date = vending_date;
    }

    public Client getClient_id() {
        return client_id;
    }

    public void setClient_id(Client client_id) {
        this.client_id = client_id;
    }

    public Employee getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Employee employee_id) {
        this.employee_id = employee_id;
    }
}
