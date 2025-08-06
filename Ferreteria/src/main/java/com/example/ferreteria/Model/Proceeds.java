package com.example.ferreteria.Model;
import jakarta.persistence.*;

@Entity
@Table(name = "supplier")

public class Proceeds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long proceeds_id;
    private String name;
    private String category;
    private String price;
    private String quantity;
    private String min_stock;
    private String supplier_id;

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getMin_stock() {
        return min_stock;
    }

    public void setMin_stock(String min_stock) {
        this.min_stock = min_stock;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public long getProceeds_id() {
        return proceeds_id;
    }

    public void setProceeds_id(long proceeds_id) {
        this.proceeds_id = proceeds_id;
    }
}
