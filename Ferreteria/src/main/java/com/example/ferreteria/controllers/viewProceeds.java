package com.example.ferreteria.controllers;

import com.example.ferreteria.Model.Proceeds;
import com.example.ferreteria.Repository.EmployeeRepository;
import com.example.ferreteria.Repository.ProceedsRepository;
import com.example.ferreteria.Repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class viewProceeds {

    @Autowired
     ProceedsRepository proceedsRepository;
    @Autowired
     SupplierRepository supplierRepository;



    @GetMapping("/vista/proceeds")
    public String lista(Model model)
    {
        model.addAttribute("proceeds", proceedsRepository.findAll());
        return "proceeds";
    }

    @GetMapping("/vistaS/form")
    public String formulario(Model model)
    {
        model.addAttribute("supplier",supplierRepository.findAll());
        model.addAttribute("proceeds", new Proceeds());
        model.addAttribute("mode", "crear");
        return "proceeds_form";
    }

    @PostMapping("/vistaS/save")
    public String save(@ModelAttribute Proceeds proceeds, RedirectAttributes su)
    {
        boolean isNew = (proceeds.getSupplier_id() == null);
        proceedsRepository.save(proceeds);

        if (isNew) {
            su.addFlashAttribute("success", "Ingreso guardado exitosamente!");
        } else {
            su.addFlashAttribute("success", "Ingreso actualizado exitosamente!");
        }

        return "redirect:/vista/proceeds";
    }

    @GetMapping("/vistaS/edit/{id}")
    public String edit(@PathVariable Long id, Model model)
    {
        Proceeds proceeds = proceedsRepository.findById(id).orElse(null);
        model.addAttribute("proceeds", proceeds);
        model.addAttribute("mode", "editar");
        return "proceeds_form";
    }

    @PostMapping("/vistaS/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes su)
    {
        proceedsRepository.deleteById(id);
        su.addFlashAttribute("success", "Ingreso eliminado exitosamente!");
        return "redirect:/vista/proceeds";
    }
}