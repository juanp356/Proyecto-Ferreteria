package com.example.ferreteria.Model;
import jakarta.persistence.*;

@Entity
@Table(name = "proceeds")

public class Proceeds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long proceeds_id;
    private String name;
    private String category;
    private int price;
    private int quantity;
    private int min_stock;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    public Long getProceeds_id() {
        return proceeds_id;
    }

    public void setProceeds_id(Long proceeds_id) {
        this.proceeds_id = proceeds_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMin_stock() {
        return min_stock;
    }

    public void setMin_stock(int min_stock) {
        this.min_stock = min_stock;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
