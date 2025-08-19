package com.example.ferreteria.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vending")

public class Vending {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="vending_id")
    private Long id;

    @Column(name="total_amount")
    private Integer totalAmount; // si tu columna es INT según captura (total en centavos)
    @Column(name="vending_date")
    private LocalDateTime vendingDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    // Extras no persistidos en tu esquema original pero útiles:
    @Transient
    private BigDecimal subtotal; // calculado
    @Enumerated(EnumType.STRING)
    @Column(length=12)
    private Status status = Status.COMPLETED; // COMPLETED / CANCELED

    @OneToMany(mappedBy="vending", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<VendingDetail> details = new ArrayList<>();

    public enum Status { COMPLETED, CANCELED }

    // helpers
    public BigDecimal getTotalAsMoney() {
        return new BigDecimal(totalAmount).movePointLeft(2); // si guardas en centavos
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getVendingDate() {
        return vendingDate;
    }

    public void setVendingDate(LocalDateTime vendingDate) {
        this.vendingDate = vendingDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<VendingDetail> getDetails() {
        return details;
    }

    public void setDetails(List<VendingDetail> details) {
        this.details = details;
    }
}
