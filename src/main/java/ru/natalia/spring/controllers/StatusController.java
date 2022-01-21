package ru.natalia.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.natalia.spring.dao.StatusDAO;
import ru.natalia.spring.models.Status;

import javax.validation.Valid;

@Controller
@RequestMapping("/statuses")
public class StatusController {
    @Autowired
    private StatusDAO statusDAO;

    @GetMapping()
    public String index(Model model){ //получим все статусы из DAO и передадим на отображение во view
        model.addAttribute("statuses",statusDAO.index());
        return "statuses/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,
                       Model model){
        model.addAttribute("status",statusDAO.show(id));
        return "statuses/show";
    }
    @GetMapping("/new")
    public String newStatus(@ModelAttribute("status") Status status){
        return "statuses/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("status") @Valid Status status,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "statuses/new";
        }
        statusDAO.save(status);
        return "redirect:/statuses";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("status",statusDAO.show(id));
        return "statuses/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("status") @Valid Status status,
                         BindingResult bindingResult,
                         @PathVariable("id") int id){
        if (bindingResult.hasErrors()){
            return "statuses/edit";
        }
        statusDAO.update(id,status);
        return "redirect:/statuses";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        statusDAO.delete(id);
        return "redirect:/statuses";
    }
}
