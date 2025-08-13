package com.example.ferreteria.Repository;

import com.example.ferreteria.Model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface SupplierRepository extends JpaRepository<Supplier,Long> {

    @Query("SELECT COUNT(c) FROM Supplier c")
    Long countSupplier();
}
