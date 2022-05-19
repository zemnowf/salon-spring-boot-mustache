package com.zemnov.salon.service.cheque;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class ChequeSaveServiceImpl implements ChequeSaveService{

    @Override
    public void saveCheque(String chequeText, Long chequeId){

        File directory = new File("cheques");
        String path = directory.getPath();

        String chequePathName = path + "/cheque" + chequeId + ".txt";
        File chequeFile = new File(chequePathName);

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(chequeFile));
            writer.write(chequeText);
            writer.flush();
            writer.close();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
