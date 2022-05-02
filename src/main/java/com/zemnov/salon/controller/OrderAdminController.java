package com.zemnov.salon.controller;

import com.zemnov.salon.model.Master;
import com.zemnov.salon.model.Order;
import com.zemnov.salon.model.ServiceType;
import com.zemnov.salon.model.User;
import com.zemnov.salon.repository.MasterRepo;
import com.zemnov.salon.repository.OrderRepo;
import com.zemnov.salon.repository.ServiceTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orderAdmin")
@PreAuthorize("hasAuthority('ADMIN')")
public class OrderAdminController {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private MasterRepo masterRepo;
    @Autowired
    private ServiceTypeRepo serviceTypeRepo;

    @GetMapping
    public String editOrders(Model model){
        List<Order> orders=orderRepo.findAll();
        model.addAttribute("orders", orders);

        return("orderAdminPage");
    }

    @GetMapping("{order}/edit")
    public String orderAdminEditForm(@PathVariable Order order, Model model) {

        List<Master> masters = masterRepo.findByRang(order.getServiceTypeName().getRang());

        model.addAttribute("masters", masters);
        model.addAttribute("order", order);

        return "orderAdminEdit";
    }

    @GetMapping("{order}/delete")
    public String orderAdminDeleteForm(@PathVariable Order order, Model model) {
        model.addAttribute("order", order);

        return "orderAdminDelete";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("orderId") Order order){
        orderRepo.deleteById(order.getId());
        return("redirect:/orderAdmin");
    }

    @PostMapping("/submit")
    public String submit(@RequestParam("orderId") Order order,
            @RequestParam String master,
            Model model) {
        String status = "submitted";

        List<Master> masters = masterRepo.findByName(master);
        Master currentMaster = masters.get(0);

        order.setMaster(currentMaster);
        order.setOrderStatus(status);
        orderRepo.save(order);

        return ("redirect:/orderAdmin");
    }
}
