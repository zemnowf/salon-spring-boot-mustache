package com.zemnov.salon.service;

import com.zemnov.salon.model.Order;
import com.zemnov.salon.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {

    @Autowired
    private OrderRepo orderRepo;

    public Map<String, Integer> countedServiceTypes(){
        List<Order> orders=orderRepo.findAll();

        Map<String, Integer> servicesAndCounts = new HashMap<String, Integer>();
        for (int i = 0; i < orders.size(); i++) {
            servicesAndCounts.put(orders.get(i).getServiceTypeName().getName(),
                    orderRepo.countAllByServiceTypeName(orders.get(i).getServiceTypeName()));
        }
        return servicesAndCounts;
    }

    public List<Order> showEarnings(){
       return orderRepo.findAll();
    }

    public Integer countHighRangServiceTypes(){
        List<Order> orders = orderRepo.findAll();
        Integer highRangServiceTypesEarning=0;
        for (int i = 0; i < orders.size(); i++) {
            if(orders.get(i).getServiceTypeName().getRang()!=3){
                highRangServiceTypesEarning+=orders.get(i).getServiceTypeName().getPrice();
            }
        }
        return highRangServiceTypesEarning;
    }

    public Integer countLowRangServiceTypes(){
        List<Order> orders = orderRepo.findAll();
        Integer lowRangServiceTypesEarning=0;
        for (int i = 0; i < orders.size(); i++) {
            if(orders.get(i).getServiceTypeName().getRang()==3){
                lowRangServiceTypesEarning+=orders.get(i).getServiceTypeName().getPrice();
            }
        }
        return lowRangServiceTypesEarning;
    }

}
