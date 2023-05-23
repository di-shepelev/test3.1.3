package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.UserServise;

import java.util.List;

@Controller
public class AdminController {
    private final UserServise userServise;

    public AdminController(UserServise userServise) {
        this.userServise = userServise;
    }

    @GetMapping(value = "/admin")
    public String listUsers(Model model) {

        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userServise.listUsers());

        return "admin";
    }

    @PostMapping("/admin/new")
    public String addUser(@ModelAttribute("user") User user, @RequestParam("rolesName") List<Role> listOfRoles) {

        userServise.addUser(user, listOfRoles);

        return "redirect:/admin";
    }

    @GetMapping("admin/{id}/edit")
    public String editUser(Model model, @PathVariable("id") int id ) {
        model.addAttribute("user", userServise.getUserById(id));

        return "edit";
    }

    @PatchMapping("admin/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id, @RequestParam(value = "rolesName",
            defaultValue = "1") List<Role> listOfRoles) {

        userServise.updateUser(user, id, listOfRoles);

        return "redirect:/admin";
    }

    @GetMapping("admin/{id}/delete")
    public String deleteUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userServise.getUserById(id));

        return "delete";
    }
    @DeleteMapping("admin/{id}")
    public String removeUser(@PathVariable("id") int id) {
        userServise.removeUser(id);

        return "redirect:/admin";
    }
}
