package com.example.ferreteria.controllers;


import com.example.ferreteria.service.ClientService;
import com.example.ferreteria.service.ProceedsService;
import com.example.ferreteria.service.SupplierService;
import com.example.ferreteria.service.VendingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class viewDashboard {

    private final ClientService clientService;
    private final ProceedsService proceedsService;
    private final VendingService vendingService;
    private final SupplierService supplierService;

    public viewDashboard(ClientService clientService,ProceedsService proceedsService,VendingService vendingService,SupplierService supplierService) {
        this.clientService = clientService;
        this.proceedsService = proceedsService;
        this.vendingService = vendingService;
        this.supplierService = supplierService;
    }



    @GetMapping("/vista/dashboard")
    public String totalClients(Model model)
    {
        Long totalClients = clientService.GetTotalCustomers();
        Long totalProceeds = proceedsService.GetTotalProceeds();
        Long totalVending = vendingService.GetTotalVending();
        Long totalSupplier = supplierService.GetTotalSupplier();
        model.addAttribute("totalClients", totalClients);
        model.addAttribute("totalProceeds",totalProceeds);
        model.addAttribute("totalVending",totalVending);
        model.addAttribute("totalSupplier",totalSupplier);

        // Ventas por mes (datos para Chart.js)
        List<Object[]> ventasMes = vendingService.getSalesPerMonth();
        List<String> labels = new ArrayList<>();
        List<Long> totals = new ArrayList<>();

        String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };

        // Inicializamos con 0 para todos los meses
        long[] ventasPorMes = new long[12];
        for (Object[] fila : ventasMes) {
            int mes = ((Number) fila[0]).intValue();
            long total = ((Number) fila[1]).longValue();
            ventasPorMes[mes - 1] = total;
        }

        for (int i = 0; i < 12; i++) {
            labels.add(meses[i]);
            totals.add(ventasPorMes[i]);
        }

        model.addAttribute("labels", labels);
        model.addAttribute("totals", totals);

        return "dashboard";
    }



}



