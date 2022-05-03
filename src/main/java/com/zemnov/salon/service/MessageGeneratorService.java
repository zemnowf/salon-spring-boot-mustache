package com.zemnov.salon.service;

import com.zemnov.salon.model.Order;
import com.zemnov.salon.model.User;
import org.springframework.stereotype.Service;

@Service
public class MessageGeneratorService {

    public String mailMessageGenerate(User user, Order order){
        String message = "Здравствуйте, " + user.getClientName() + "!\n" +
                "Вы оформили запись в нашем салоне на услугу " + order.getServiceTypeName().getServiceGroup() +
                "(" + order.getServiceTypeName().getName() + ")" + "\n"
                + "Ждём вас " + order.getOrderTime() + " " + order.getOrderDate() + ".\n"
                + "Ваш мастер - " + order.getMaster().getName() + " " + order.getMaster().getSurname() + ".\n"
                + "С уважением,  салон DZNTS.";
        return message;
    }

    public String chequeMessageGenerate(User user, Order order){
        String chequeText = "Клиент: " + user.getClientName() + "!\n" +
                "Услуга: " + order.getServiceTypeName().getServiceGroup() +
                "(" + order.getServiceTypeName().getName() + ")" + "\n"
                + "Стоимость:" + order.getServiceTypeName().getPrice() + "\n"
                + "Дата: " + order.getOrderTime() + " " + order.getOrderDate() + ".\n"
                + "Мастер: " + order.getMaster().getName() + " " + order.getMaster().getSurname() + ".\n"
                + "Салон DZNTS.";
        return chequeText;
    }

}
