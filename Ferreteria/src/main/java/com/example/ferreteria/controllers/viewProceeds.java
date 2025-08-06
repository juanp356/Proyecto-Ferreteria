package com.example.ferreteria.controllers;

import com.example.ferreteria.Model.Employee;
import com.example.ferreteria.Model.Proceeds;
import com.example.ferreteria.Repository.ProceedsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class viewProceeds {

    @Autowired
    ProceedsRepository proceedsRepository;

    @GetMapping("/vista/proceeds")
    public String lista(Model model)
    {
        model.addAttribute("proceeds", proceedsRepository.findAll());
        return "proceeds";
    }

    @GetMapping("/vistaP/form")
    public String formulario(Model model)
    {
        model.addAttribute("proceeds", new Proceeds());
        model.addAttribute("mode", "crear");
        return "proceeds_form";
    }

    @PostMapping("/vistaP/save")
    public String save(@ModelAttribute Proceeds proceeds, RedirectAttributes em)
    {
        boolean isNew = (proceeds.getProceeds_id() == null);
        proceedsRepository.save(proceeds);

        if (isNew) {
            em.addFlashAttribute("success", "Producto guardado exitosamente!");
        } else {
            em.addFlashAttribute("success", "PRoducto editado exitosamente!");
        }

        return "redirect:/vista/proceeds";
    }

    @GetMapping("/vistaE/edit/{id}")
    public String edit(@PathVariable Long id, Model model)
    {
        Employee employee = proceedsRepository.findById(id).orElse(null);
        model.addAttribute("proceeds",proceeds);
        model.addAttribute("mode", "editar");
        return "proceeds_form";
    }

    @PostMapping("/vistaE/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes em)
    {
        proceedsRepository.deleteById(id);
        em.addFlashAttribute("success", "Producto eliminado exitosamente!");
        return "redirect:/vista/proceeds";
    }

}
