package com.example.ferreteria.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "vending")

public class Vending {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Long vending_id;
        private String total_amount;
        private String vending_date;
        private String client_id;
        private String employee_id;

    public Long getVending_id() {
        return vending_id;
    }

    public void setVending_id(Long vending_id) {
        this.vending_id = vending_id;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getVending_date() {
        return vending_date;
    }

    public void setVending_date(String vending_date) {
        this.vending_date = vending_date;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }
}
