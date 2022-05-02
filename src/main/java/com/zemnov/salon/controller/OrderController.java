package com.zemnov.salon.controller;

import com.zemnov.salon.model.Master;
import com.zemnov.salon.model.Order;
import com.zemnov.salon.model.ServiceType;
import com.zemnov.salon.model.User;
import com.zemnov.salon.repository.MasterRepo;
import com.zemnov.salon.repository.OrderRepo;
import com.zemnov.salon.repository.ServiceTypeRepo;
import com.zemnov.salon.service.ChequeSaveService;
import com.zemnov.salon.service.SmtpMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private ServiceTypeRepo serviceTypeRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private MasterRepo masterRepo;
    @Autowired
    private SmtpMailSender mailSender;
    @Autowired
    private ChequeSaveService chequeSaveService;


    @GetMapping("/orders")
    public String show(@AuthenticationPrincipal User user,
                       Model model){

        List<Order> orders=orderRepo.findByClient(user);
        model.addAttribute("orders", orders);

        return("orderUserList");
    }

    @GetMapping("/editOrders")
    public String editOrders(Model model){
        List<Order> orders=orderRepo.findAll();
        model.addAttribute("orders", orders);

        return("orderAdminPage");
    }

    @GetMapping
    public String main(Model model) {
        List<ServiceType> serviceTypes = serviceTypeRepo.findAll();

        HashSet<String> serviceGroups = new HashSet<>();
        for (int i = 0; i < serviceTypes.size(); i++) {
            serviceGroups.add(serviceTypes.get(i).getServiceGroup());
        }

        model.addAttribute("serviceGroups", serviceGroups);

        return "orders";
    }

    @GetMapping("/details")
    public String detail(@RequestParam String serviceGroup,
                         Model model){
        List<ServiceType> serviceTypes = serviceTypeRepo.findByServiceGroup(serviceGroup);

        model.addAttribute("serviceTypes", serviceTypes);
        model.addAttribute("serviceGroup", serviceGroup);

        return("orderDetails");
    }

    @GetMapping("/chooseMaster")
    public String chooseMaster(@RequestParam String serviceGroup,
                               @RequestParam String serviceType,
                               @RequestParam String date,
                               @RequestParam String time,
                               Model model){

        List<ServiceType> serviceTypes = serviceTypeRepo.findByName(serviceType);
        ServiceType currentService = serviceTypes.get(0);
        Integer currentRang = currentService.getRang();
        List<Master> masters = masterRepo.findByRang(currentRang);


        model.addAttribute("serviceType", serviceType);
        model.addAttribute("serviceGroup", serviceGroup);
        model.addAttribute("date", date);
        model.addAttribute("time", time);
        model.addAttribute("masters", masters);

        return("orderSubmit");
    }

    @PostMapping("/submit")
    public String submit(
                            @AuthenticationPrincipal User user,
                            @RequestParam String serviceType,
                            @RequestParam String date,
                            @RequestParam String time,
                            @RequestParam String master,
                         Model model) {
        String status = "processing";
        List<ServiceType> serviceTypes = serviceTypeRepo.findByName(serviceType);
        ServiceType currentService = serviceTypes.get(0);

        List<Master> masters = masterRepo.findByName(master);
        Master currentMaster = masters.get(0);

        Order order = new Order(user, currentService, currentMaster, date, time, status);

        String message = "Здравствуйте, " + user.getClientName() + "!\n" +
                "Вы оформили запись в нашем салоне на услугу " + order.getServiceTypeName().getServiceGroup() +
                "(" + order.getServiceTypeName().getName() + ")" + "\n"
                + "Ждём вас " + order.getOrderTime() + " " + order.getOrderDate() + ".\n"
                + "Ваш мастер - " + order.getMaster().getName() + " " + order.getMaster().getSurname() + ".\n"
                + "С уважением,  салон DZNTS.";

        String chequeText = "Клиент: " + user.getClientName() + "!\n" +
                "Услуга: " + order.getServiceTypeName().getServiceGroup() +
                "(" + order.getServiceTypeName().getName() + ")" + "\n"
                + "Стоимость:" + order.getServiceTypeName().getPrice() + "\n"
                + "Дата: " + order.getOrderTime() + " " + order.getOrderDate() + ".\n"
                + "Мастер: " + order.getMaster().getName() + " " + order.getMaster().getSurname() + ".\n"
                + "Салон DZNTS.";

        mailSender.send(user.getMail(),"Запись оформлена", message);

        orderRepo.save(order);

        chequeSaveService.saveCheque(chequeText, order.getId());

        return ("orderSuccess");
    }

}
