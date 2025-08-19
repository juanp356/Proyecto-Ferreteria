package com.example.ferreteria.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "vending_detail")

public class VendingDetail {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="vdetail_id") private Long id;

    private Integer quantity;
    @Column(name="unit_price")
    private Integer unitPrice; // en centavos
    private Integer subtotal; // en centavos

    @ManyToOne
    @JoinColumn(name="vending_id")
    private Vending vending;
    @ManyToOne
    @JoinColumn(name="proceeds_id")
    private Proceeds proceeds;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }

    public Vending getVending() {
        return vending;
    }

    public void setVending(Vending vending) {
        this.vending = vending;
    }

    public Proceeds getProceeds() {
        return proceeds;
    }

    public void setProceeds(Proceeds proceeds) {
        this.proceeds = proceeds;
    }
}
