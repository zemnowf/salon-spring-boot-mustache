package com.zemnov.salon.service.master;

import com.zemnov.salon.dto.MasterCreateRequestDto;
import com.zemnov.salon.model.Master;
import com.zemnov.salon.repository.MasterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterServiceImpl implements MasterService {

    @Autowired
    private MasterRepo masterRepo;

    @Override
    public List<Master> findMasters(Integer rang){

        List<Master> masters;

        if(rang != 1) {
            masters = masterRepo.findByRang(rang);
        } else masters = masterRepo.findAll();

        return masters;
    }

    @Override
    public void saveMaster(Master master, String name, String surname, Integer rang){

        master.setName(name);
        master.setSurname(surname);
        master.setRang(rang);
        masterRepo.save(master);

    }

    @Override
    public void deleteMaster(Master master){

        masterRepo.deleteById(master.getId());

    }

    @Override
    public void addMaster(MasterCreateRequestDto masterCreateRequestDto){

        Master master = new Master(masterCreateRequestDto.getName(), masterCreateRequestDto.getSurname(),
                masterCreateRequestDto.getRang());
        masterRepo.save(master);

    }

}
