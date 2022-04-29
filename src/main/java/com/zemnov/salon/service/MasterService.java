package com.zemnov.salon.service;

import com.zemnov.salon.repository.MasterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterService{
    @Autowired
    private MasterRepo masterRepo;



}
