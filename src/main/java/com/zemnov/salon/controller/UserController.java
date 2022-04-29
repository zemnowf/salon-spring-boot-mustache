package com.zemnov.salon.controller;

import com.zemnov.salon.model.Contact;
import com.zemnov.salon.model.Role;
import com.zemnov.salon.model.User;
import com.zemnov.salon.repository.ContactRepo;
import com.zemnov.salon.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ContactRepo contactRepo;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
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
            @RequestParam("userId") User user
    ) {

        contactRepo.deleteById(user.getId()+1);

        return "redirect:/user";
    }

    @PostMapping("/edit")
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user,
            @RequestParam String clientName,
            @RequestParam String number,
            @RequestParam String mail
    ) {
        user.setUsername(username);
        user.setClientName(clientName);
        user.setNumber(number);
        user.setMail(mail);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);

        Contact contact = new Contact(clientName, number, mail, user);
        contactRepo.save(contact);


        return "redirect:/user";
    }
}
