package com.zemnov.salon.controller;

import com.zemnov.salon.model.Contact;
import com.zemnov.salon.model.User;
import com.zemnov.salon.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

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

        User userFromDb = registrationService.findUserByUsername(user);

        if(userFromDb != null) {
            model.put("message", "Пользователь уже существует");
            return "registration";
        }

        registrationService.saveUser(user);

        Contact contact = new Contact(clientName, number, mail, user);
        registrationService.saveContact(contact);

        return "redirect:/login";
    }


}
