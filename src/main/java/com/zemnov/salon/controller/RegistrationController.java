package com.zemnov.salon.controller;

import com.zemnov.salon.model.Contact;
import com.zemnov.salon.model.Role;
import com.zemnov.salon.model.User;
import com.zemnov.salon.repository.ContactRepo;
import com.zemnov.salon.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ContactRepo contactRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user,
                          @RequestParam String clientName,
                          @RequestParam String number,
                          @RequestParam String mail,
                          Map<String, Object> model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if(userFromDb != null) {
            model.put("message", "Пользователь уже существует");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        Contact contact = new Contact(clientName, number, mail, user);
        contactRepo.save(contact);

        return "redirect:/login";
    }


}
