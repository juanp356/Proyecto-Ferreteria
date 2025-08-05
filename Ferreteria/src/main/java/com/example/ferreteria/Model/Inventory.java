package com.example.ferreteria.Model;
import jakarta.persistence.*;

@Entity
@Table(name = "supplier")

public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String name;
    private String category;
    private String contact;
    private String price;
    private String products_supplied;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProducts_supplied() {
        return products_supplied;
    }

    public void setProducts_supplied(String products_supplied) {
        this.products_supplied = products_supplied;
    }
}
