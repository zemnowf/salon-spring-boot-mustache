package com.zemnov.salon.repository;

import com.zemnov.salon.model.Order;
import com.zemnov.salon.model.ServiceType;
import com.zemnov.salon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long>{
    List<Order> findByClient(User user);
    int countAllByServiceTypeName(ServiceType serviceTypeName);
}
