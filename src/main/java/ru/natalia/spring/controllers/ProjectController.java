package ru.natalia.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.natalia.spring.dao.ProjectDAO;
import ru.natalia.spring.models.Project;

import javax.validation.Valid;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectDAO projectDAO;

    public ProjectController(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("projects",projectDAO.index());
        return "projects/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,
                       Model model){
        model.addAttribute("project",projectDAO.show(id));
        return "projects/show";
    }
    @GetMapping("/new")
    public String newStatus(@ModelAttribute("project") Project project){
        return "projects/new";
    }
    @PostMapping("")
    public String create(@ModelAttribute("project") @Valid Project project,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return"projects/new";
        }
        projectDAO.save(project);
        return "redirect:/projects";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("project",projectDAO.show(id));
        return "projects/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("project") @Valid Project project,
                         BindingResult bindingResult,
                         @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "projects/edit";
        }
        projectDAO.update(id,project);
        return "redirect:/projects";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        projectDAO.delete(id);
        return "redirect:/projects";
    }

}
