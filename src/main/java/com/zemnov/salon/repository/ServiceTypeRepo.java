package com.zemnov.salon.repository;

import com.zemnov.salon.model.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceTypeRepo extends JpaRepository<ServiceType, Long> {

    List<ServiceType> findByRang(Integer rang);
    List<ServiceType> findByServiceGroup(String serviceGroup);
    List<ServiceType> findByName(String name);
}
