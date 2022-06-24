package com.zemnov.salon.controller;

import com.zemnov.salon.dto.UserEditRequestDto;
import com.zemnov.salon.model.Role;
import com.zemnov.salon.model.User;
import com.zemnov.salon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String userList(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping("{user}/edit")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }

    @GetMapping("{user}/delete")
    public String userDeleteForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userDelete";
    }

    @PostMapping("/delete")
    public String userDelete(
            @RequestParam("userId") User user) {

        userService.deleteUserById(user);

        return "redirect:/user";
    }

    @PostMapping("/edit")
    public String userSave(
            @RequestParam("userId") User user,
            @RequestBody UserEditRequestDto userEditRequestDto) {

        userService.saveUser(user, userEditRequestDto);
        userService.saveContact(user, userEditRequestDto);

        return "redirect:/user";
    }
}
