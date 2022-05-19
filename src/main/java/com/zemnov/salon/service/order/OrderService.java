package com.zemnov.salon.service.order;

import com.zemnov.salon.model.Master;
import com.zemnov.salon.model.Order;
import com.zemnov.salon.model.ServiceType;
import com.zemnov.salon.model.User;

import java.util.HashSet;
import java.util.List;

public interface OrderService {
    public List<Order> findOrdersByClient(User user);

    public List<Order> findAllOrders();

    public HashSet<String> findServiceGroups();

    public List<ServiceType> findByServiceGroups(String serviceGroup);

    public List<ServiceType> findTypesByName(String serviceType);

    public List<Master> findMastersByRang(Integer currentRang);

    public List<Master> findMasterByName(String master);

    public void saveOrder(Order order);
}
