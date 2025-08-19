package com.example.ferreteria.service;

import com.example.ferreteria.Model.*;
import com.example.ferreteria.Repository.ProceedsRepository;
import com.example.ferreteria.Repository.VendingRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VendingService {

    private final VendingRepository vendingRepository;
    private final ProceedsRepository proceedsRepository;

    public VendingService(VendingRepository vendingRepository,ProceedsRepository proceedsRepository){
        this.vendingRepository = vendingRepository;
        this.proceedsRepository = proceedsRepository;
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

    @Transactional
    public Vending confirmSale(Long clientId, Long employeeId, List<CartItem> items, int paidCents){
        if(items.isEmpty()) throw new IllegalStateException("El carrito está vacío");

        // validar stock
        Map<Long, Proceeds> products = new HashMap<>();
        for (CartItem it: items){
            Proceeds p = proceedsRepository.findById(it.proceedsId).orElseThrow();
            if(p.getQuantity() < it.qty) throw new IllegalStateException("Stock insuficiente de " + p.getName());
            products.put(p.getProceeds_id(), p);
        }

        // crear venta
        Vending v = new Vending();
        v.setClient(clientId==null? null : new Client(){ { setClient_id(clientId);} });
        v.setEmployee(employeeId==null? null : new Employee(){ { setEmployee_id(employeeId);} });

        int totalCents = items.stream().mapToInt(CartItem::getSubtotalCents).sum();
        v.setTotalAmount(totalCents); // INT en centavos
        v.setStatus(Vending.Status.COMPLETED);

        // detalles + descuento de stock
        for (CartItem it: items){
            Proceeds p = products.get(it.proceedsId);
            p.setQuantity(p.getQuantity() - it.qty); // descuenta stock

            VendingDetail d = new VendingDetail();
            d.setProceeds(p);
            d.setQuantity(it.qty);
            d.setUnitPrice(it.unitPriceCents);
            d.setSubtotal(it.getSubtotalCents());
            d.setVending(v);
            v.getDetails().add(d);
        }
        proceedsRepository.saveAll(products.values());
        return vendingRepository.save(v);
    }

    @Transactional
    public void cancelConfirmedSale(Long saleId){
        Vending v = vendingRepository.findById(saleId).orElseThrow();
        if (v.getStatus()== Vending.Status.CANCELED) return;
        // devolver stock
        for (VendingDetail d : v.getDetails()){
            Proceeds p = d.getProceeds();
            p.setQuantity(p.getQuantity() + d.getQuantity());
        }
        v.setStatus(Vending.Status.CANCELED);
    }
}
