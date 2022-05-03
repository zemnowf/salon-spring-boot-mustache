package com.zemnov.salon.controller;

import com.zemnov.salon.model.Master;
import com.zemnov.salon.model.Order;
import com.zemnov.salon.model.ServiceType;
import com.zemnov.salon.model.User;
import com.zemnov.salon.repository.MasterRepo;
import com.zemnov.salon.repository.OrderRepo;
import com.zemnov.salon.repository.ServiceTypeRepo;
import com.zemnov.salon.service.OrderAdminService;
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
    private OrderAdminService orderAdminService;

    @GetMapping
    public String editOrders(Model model){

        List<Order> orders=orderAdminService.findAllOrders();
        model.addAttribute("orders", orders);

        return("orderAdminPage");
    }

    @GetMapping("{order}/edit")
    public String orderAdminEditForm(@PathVariable Order order, Model model) {

        List<Master> masters = orderAdminService.orderAdminEditForm(order);

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
        orderAdminService.deleteOrder(order);
        return("redirect:/orderAdmin");
    }

    @PostMapping("/submit")
    public String submit(@RequestParam("orderId") Order order,
            @RequestParam String master,
            Model model) {
        String status = "submitted";

        orderAdminService.orderAdminSubmit(order, master);

        return ("redirect:/orderAdmin");
    }
}
