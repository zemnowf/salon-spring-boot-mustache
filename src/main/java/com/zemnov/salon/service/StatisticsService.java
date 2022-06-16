package com.zemnov.salon.service;

import com.zemnov.salon.model.Order;
import com.zemnov.salon.model.ServiceType;
import com.zemnov.salon.repository.OrderRepo;
import com.zemnov.salon.repository.ServiceTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class StatisticsService {

    static final Integer ZERO_EARNINGS = 0;
    static final Integer MASTER_LOW_RANG = 3;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ServiceTypeRepo serviceTypeRepo;

    public Map<String, Integer> countedServiceTypes(){

        List<String> groupServiceTypes = serviceTypeRepo.groupServiceTypes();
        List<Integer> countServiceTypes = orderRepo.countServiceTypes();
        Map<String, Integer> servicesAndCounts = new HashMap<String, Integer>();

        for (int i = 0; i < groupServiceTypes.size(); i++) {
            servicesAndCounts.put(groupServiceTypes.get(i), countServiceTypes.get(i));
        }
        //List<Order> orders=orderRepo.findAll();

        //Map<String, Integer> servicesAndCounts = new HashMap<String, Integer>();
        //for (int i = 0; i < orders.size(); i++) {
        //    servicesAndCounts.put(orders.get(i).getServiceTypeName().getName(),
        //            orderRepo.countAllByServiceTypeName(orders.get(i).getServiceTypeName()));
        //}
        return servicesAndCounts;
    }

    public List<Order> getOrders(){
       return orderRepo.findAll();
    }

    public Integer countHighRangServiceTypes(){
        List<Order> orders = orderRepo.findAll();
        Integer highRangServiceTypesEarning=ZERO_EARNINGS;
        for (int i = 0; i < orders.size(); i++) {
            if(!Objects.equals(orders.get(i).getServiceTypeName().getRang(), MASTER_LOW_RANG)){
                highRangServiceTypesEarning+=orders.get(i).getServiceTypeName().getPrice();
            }
        }
        return highRangServiceTypesEarning;
    }

    public Integer countLowRangServiceTypes(){
        List<Order> orders = orderRepo.findAll();
        Integer lowRangServiceTypesEarning=ZERO_EARNINGS;
        for (int i = 0; i < orders.size(); i++) {
            if(Objects.equals(orders.get(i).getServiceTypeName().getRang(), MASTER_LOW_RANG)){
                lowRangServiceTypesEarning+=orders.get(i).getServiceTypeName().getPrice();
            }
        }
        return lowRangServiceTypesEarning;
    }

}
