package com.zemnov.salon.controller;

import com.zemnov.salon.model.Master;
import com.zemnov.salon.model.Order;
import com.zemnov.salon.model.ServiceType;
import com.zemnov.salon.model.User;
import com.zemnov.salon.repository.MasterRepo;
import com.zemnov.salon.repository.OrderRepo;
import com.zemnov.salon.repository.ServiceTypeRepo;
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
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private ServiceTypeRepo serviceTypeRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private MasterRepo masterRepo;

    @GetMapping("/orders")
    public String show(@AuthenticationPrincipal User user,
                       Model model){

        List<Order> orders=orderRepo.findByClient(user);
        model.addAttribute("orders", orders);

        return("orderUserList");
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

        orderRepo.save(order);

        return ("orderSuccess");
    }

}
