package com.zemnov.salon.utils;

import com.zemnov.salon.model.User;
import com.zemnov.salon.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationValidationService {

    @Autowired
    ValidationService validationService;

    @Autowired
    private RegistrationService registrationService;

    public String isUserValid(User user, String name, String number, String mail){

        User userFromDb = registrationService.findUserByUsername(user);

        if(!validationService.validateEmptyLines(user.getUsername())){
            return "Заполните поле логина";
        } else if(!validationService.validateEmptyLines(user.getPassword())){
            return "Пароль должен содержать по крайней мере 8 символов";
        } else if(!validationService.validateEmptyLines(name)){
            return "Заполните поле имени";
        } else if(!validationService.isValidPhoneNumber(number)){
            return "Неверный формат номера телефона";
        } else if(!validationService.isValidMail(mail)){
            return "Неверный формат почты";
        } else if(userFromDb != null) {
            return "Пользователь уже существует";
        } else return null;
    }
}


