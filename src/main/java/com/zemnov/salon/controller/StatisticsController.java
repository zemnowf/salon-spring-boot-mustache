package com.zemnov.salon.controller;

import com.zemnov.salon.model.Order;
import com.zemnov.salon.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/statistics")
@PreAuthorize("hasAuthority('ADMIN')")
public class StatisticsController {

    @Autowired
    private OrderRepo orderRepo;

    @GetMapping("/charts")
    public String showCharts(Model model){
        List<Order> orders=orderRepo.findAll();

        Map<String, Integer> servicesAndCounts = new HashMap<String, Integer>();
        for (int i = 0; i < orders.size(); i++) {
            servicesAndCounts.put(orders.get(i).getServiceTypeName().getName(),
                    orderRepo.countAllByServiceTypeName(orders.get(i).getServiceTypeName()));
        }

        model.addAttribute("map", servicesAndCounts);
        return("charts");
    }
}
