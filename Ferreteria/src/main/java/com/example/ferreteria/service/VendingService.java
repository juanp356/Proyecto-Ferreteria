package com.example.ferreteria.service;

import com.example.ferreteria.Repository.VendingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendingService {

    private final VendingRepository vendingRepository;

    public VendingService(VendingRepository vendingRepository){
        this.vendingRepository = vendingRepository;
    }
    public Long GetTotalVending(){
        return vendingRepository.countVending();

    }

    public List<Object[]> getSalesPerMonth() {
        return vendingRepository.getTotalSalesPerMonth();
    }

    public Double TotalVending(){
        return vendingRepository.countTotalVending();
    }

    public int getAverageSales(){
        return vendingRepository.getAverageSales();
    }
}
