package ru.natalia.spring.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.natalia.spring.binders.WorkaroundPropertyEditor;
import ru.natalia.spring.dao.ProjectWorkaroundDAO;
import ru.natalia.spring.models.ProjectWorkaround;
import ru.natalia.spring.models.Workaround;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/project-workarounds")
public class ProjectWorkaroundController {
    @Autowired
    private ProjectWorkaroundDAO projectWorkaroundDAO;


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("projectWorkarounds", projectWorkaroundDAO.index());
        return "project-workarounds/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,
                       Model model) {
        model.addAttribute("projectWorkaround", projectWorkaroundDAO.show(id));
        return "project-workarounds/show";
    }

    @GetMapping("/new")
    public String newProject(@ModelAttribute("projectWorkaround") ProjectWorkaround projectWorkaround, Model model) {
        List<Workaround> workarounds = projectWorkaroundDAO.findAllWorkarounds();
        model.addAttribute("workarounds", workarounds);
        return "project-workarounds/new";
    }

   @PostMapping()
    public String create(@ModelAttribute("projectWorkaround") @Valid ProjectWorkaround projectWorkaround,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "project-workarounds/new";
        }
        projectWorkaroundDAO.save(projectWorkaround);
        return "redirect:/project-workarounds";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("projectWorkaround", projectWorkaroundDAO.show(id));
        List<Workaround> workarounds = projectWorkaroundDAO.findAllWorkarounds();
        model.addAttribute("workarounds", workarounds);
        return "project-workarounds/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("projectWorkaround") @Valid ProjectWorkaround projectWorkaround,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "project-workarounds/edit";
        }
        projectWorkaroundDAO.update(id, projectWorkaround);
        return "redirect:/project-workarounds";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        projectWorkaroundDAO.delete(id);
        return "redirect:/project-workarounds";
    }

    @InitBinder
    public void initBinderAll(WebDataBinder binder)
    {
        binder.registerCustomEditor(Workaround.class,  new WorkaroundPropertyEditor(projectWorkaroundDAO));
    }
}
