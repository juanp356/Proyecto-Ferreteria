package com.example.ferreteria.controllers;

import com.example.ferreteria.Model.Supplier;
import com.example.ferreteria.Repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller

public class SupplierController {

    @Autowired
    SupplierRepository supplierRepository;



    @GetMapping("/vista/supplier")
    public String lista(Model model)
    {
        model.addAttribute("supplier", supplierRepository.findAll());
        return "supplier";
    }

    @GetMapping("/vistaS/form")
    public String formulario(Model model)
    {
        model.addAttribute("supplier", new Supplier());
        model.addAttribute("mode", "crear");
        return "supplier_form";
    }

    @PostMapping("/vistaS/save")
    public String save(@ModelAttribute Supplier supplier, RedirectAttributes su)
    {
        boolean isNew = (supplier.getSupplier_id() == null);
        supplierRepository.save(supplier);

        if (isNew) {
            su.addFlashAttribute("success", "Proveedor guardado exitosamente!");
        } else {
            su.addFlashAttribute("success", "Proveedor editado exitosamente!");
        }

        return "redirect:/vista/supplier";
    }

    @GetMapping("/vistaS/edit/{id}")
    public String edit(@PathVariable Long id, Model model)
    {
        Supplier supplier = supplierRepository.findById(id).orElse(null);
        model.addAttribute("supplier", supplier);
        model.addAttribute("mode", "editar");
        return "supplier_form";
    }

    @PostMapping("/vistaS/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes su)
    {
        supplierRepository.deleteById(id);
        su.addFlashAttribute("success", "Proveedor eliminado exitosamente!");
        return "redirect:/vista/supplier";
    }
}
