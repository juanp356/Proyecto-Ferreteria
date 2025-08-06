package com.example.ferreteria.controllers;


import com.example.ferreteria.Model.Client;
import com.example.ferreteria.Model.Employee;
import com.example.ferreteria.Repository.ClientRepository;
import com.example.ferreteria.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class viewEmployee {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/vista/employee")
    public String lista(Model model)
    {
        model.addAttribute("employees", employeeRepository.findAll());
        return "employee";
    }

    @GetMapping("/vistaE/form")
    public String formulario(Model model)
    {
        model.addAttribute("employee", new Employee());
        model.addAttribute("mode", "crear");
        return "employee_form";
    }

    @PostMapping("/vistaE/save")
    public String save(@ModelAttribute Employee employee, RedirectAttributes em)
    {
        boolean isNew = (employee.getEmployee_id() == null);
         employeeRepository.save(employee);

        if (isNew) {
            em.addFlashAttribute("success", "Empleado guardado exitosamente!");
        } else {
            em.addFlashAttribute("success", "Empleado editado exitosamente!");
        }

        return "redirect:/vista/employee";
    }

    @GetMapping("/vistaE/edit/{id}")
    public String edit(@PathVariable Long id, Model model)
    {
        Employee employee = employeeRepository.findById(id).orElse(null);
        model.addAttribute("employee", employee);
        model.addAttribute("mode", "editar");
        return "employee_form";
    }

    @PostMapping("/vistaE/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes em)
    {
        employeeRepository.deleteById(id);
        em.addFlashAttribute("success", "Empleado eliminado exitosamente!");
        return "redirect:/vista/employee";
    }

}
