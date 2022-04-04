package ru.natalia.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.natalia.spring.dao.WorkaroundDAO;
import ru.natalia.spring.models.Workaround;


import javax.validation.Valid;

@Controller
@RequestMapping("/workarounds")
public class WorkaroundController {

    @Autowired
    private WorkaroundDAO workaroundDAO;

    @GetMapping()
    public String index(Model model) { //получим все статусы из DAO и передадим на отображение во view
        model.addAttribute("workarounds", workaroundDAO.index());
        return "workarounds/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,
                       Model model) {
        model.addAttribute("workaround", workaroundDAO.show(id));
        return "workarounds/show";
    }

    @GetMapping("/new")
    public String newStatus(@ModelAttribute("workaround") Workaround workaround) {
        return "workarounds/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("workaround") @Valid Workaround workaround,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "workarounds/new";
        }
        workaroundDAO.save(workaround);
        return "redirect:/workarounds";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("workaround", workaroundDAO.show(id));
        return "workarounds/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("workaround") @Valid Workaround workaround,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "workarounds/edit";
        }
        workaroundDAO.update(id, workaround);
        return "redirect:/workarounds";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        workaroundDAO.delete(id);
        return "redirect:/workarounds";
    }
}
