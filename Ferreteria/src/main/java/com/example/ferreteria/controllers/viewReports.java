package com.example.ferreteria.controllers;


import com.example.ferreteria.service.ClientService;
import com.example.ferreteria.service.VendingService;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class viewReports {

    private final VendingService vendingService;
    private final ClientService clientService;

    public viewReports(VendingService vendingService,ClientService clientService){
        this.vendingService = vendingService;
        this.clientService = clientService;
    }


    @GetMapping("/vista/reports")
    public String reports(Model model)
    {
        Double totalVending = vendingService.TotalVending();
        Long totalCustomersR = clientService.TotalCustomers();
        int getAverageSales = vendingService.getAverageSales();
        model.addAttribute("totalVending",totalVending);
        model.addAttribute("totalCustomersR",totalCustomersR);
        model.addAttribute("getAveragesales",getAverageSales);

        return "reports";
    }


}
