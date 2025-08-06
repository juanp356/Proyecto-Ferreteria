package com.example.ferreteria.Repository;

import com.example.ferreteria.Model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository   extends JpaRepository<Supplier,Long> {
}
