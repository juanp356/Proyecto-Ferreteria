package com.example.ferreteria.service;

import com.example.ferreteria.Repository.SupplierRepository;
import com.example.ferreteria.Repository.VendingRepository;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }
    public Long GetTotalSupplier(){
        return supplierRepository.countSupplier();

    }
}
