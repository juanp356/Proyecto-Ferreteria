package com.example.ferreteria.Model;
import jakarta.persistence.*;

@Entity
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long supplier_id;
    private String name;
    private String charge;
    private String salary;

    public Long getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(Long supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
