package com.example.ferreteria.controllers;


import com.example.ferreteria.Model.Client;
import com.example.ferreteria.Repository.ClientRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class viewClient {

    @Autowired
    ClientRepository clientRepository;




    @GetMapping("/vista/client")
    public String lista(Model model)
    {
        model.addAttribute("client", clientRepository.findAll());
        return "client";
    }

    @GetMapping("/vistaC/form")
    public String formulario(Model model)
    {
        model.addAttribute("client", new Client());
        model.addAttribute("mode", "crear");
        return "client_form";
    }

    @PostMapping("/vistaC/save")
    public String save(@ModelAttribute Client client, RedirectAttributes cl)
    {
        boolean isNew = (client.getClient_id() == null);
        clientRepository.save(client);

        if (isNew) {
            cl.addFlashAttribute("success", "Cliente guardado exitosamente!");
        } else {
            cl.addFlashAttribute("success", "Cliente editado exitosamente!");
        }

        return "redirect:/vista/client";
    }

    @GetMapping("/vistaC/edit/{id}")
    public String edit(@PathVariable Long id, Model model)
    {
        Client client = clientRepository.findById(id).orElse(null);
        model.addAttribute("client", client);
        model.addAttribute("mode", "editar");
        return "client_form";
    }

    @PostMapping("/vistaC/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ac)
    {
        clientRepository.deleteById(id);
        ac.addFlashAttribute("success", "Cliente eliminado exitosamente!");
        return "redirect:/vista/client";
    }
}
