package com.zemnov.salon.service;

import com.zemnov.salon.model.Master;
import com.zemnov.salon.repository.MasterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterService{
    @Autowired
    private MasterRepo masterRepo;

    public List<Master> findMasters(Integer rang){
        List<Master> masters = masterRepo.findAll();

        if(rang != 1) {
            masters = masterRepo.findByRang(rang);
        } else masters = masterRepo.findAll();

        return masters;
    }

    public void masterSave(Master master, String name, String surname, Integer rang){
        master.setName(name);
        master.setSurname(surname);
        master.setRang(rang);
        masterRepo.save(master);
    }

    public void masterDelete(Master master){
        masterRepo.deleteById(master.getId());
    }

    public void masterAdd(String name, String surname, Integer rang){
        Master master = new Master(name, surname, rang);
        masterRepo.save(master);
    }



}
