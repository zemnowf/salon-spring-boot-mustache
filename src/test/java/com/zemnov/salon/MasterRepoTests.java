package com.zemnov.salon;

import com.zemnov.salon.model.Master;
import com.zemnov.salon.repository.MasterRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class MasterRepoTests {

    @Autowired
    private MasterRepo masterRepo;

    @Test
    public void masterSave(){
        Master master = new Master();
        String name="Tony";
        String surname="Topaz";
        Integer rang=3;

        master.setName(name);
        master.setSurname(surname);
        master.setRang(rang);
        masterRepo.save(master);
        Assertions.assertNotNull(master.getName());
        Assertions.assertNotNull(master.getSurname());
        Assertions.assertNotNull(master.getRang());
    }

    @Test
    public void masterDelete(){
        Master master = new Master("Tony", "Topaz", 3);
        masterRepo.save(master);
        masterRepo.deleteById(master.getId());
        Assertions.assertEquals(masterRepo.findById(master.getId()), Optional.empty());
    }


}
