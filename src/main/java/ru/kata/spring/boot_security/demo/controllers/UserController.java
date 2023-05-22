package ru.kata.spring.boot_security.demo.controllers;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserServise;

import java.security.Principal;


@Controller
public class UserController {

    private final UserServise userServise;

    public UserController(UserServise userServise) {
        this.userServise = userServise;
    }
    @GetMapping(value = "/user")
    public String pageUser(Principal principal, Model model) {

        User user = userServise.findByUsername(principal.getName());

        model.addAttribute("userInfo", user);

        return "user";
    }

    @GetMapping(value = "/admin")
    public String listUsers(Model model) {

        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userServise.listUsers());

        return "admin";
    }

    @PostMapping("/admin/new")
    public String addUser(@ModelAttribute("user") User user) {

        userServise.addUser(user);

        return "redirect:/admin";
    }

    @GetMapping("admin/{id}/edit")
    public String editUser(Model model, @PathVariable("id") int id ) {
        model.addAttribute("user", userServise.getUserById(id));

        return "edit";
    }

    @PatchMapping("admin/{id}")
    public String updateUser(@ModelAttribute("user") User user) {

        userServise.updateUser(user);

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
