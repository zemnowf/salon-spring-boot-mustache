package com.zemnov.salon.repository;

import com.zemnov.salon.model.Master;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MasterRepo extends JpaRepository<Master, Long> {

    List<Master> findByRang(Integer rang);
    List<Master> findByName(String name);
}
