package com.zemnov.salon.controller;

import com.zemnov.salon.model.Master;
import com.zemnov.salon.model.User;
import com.zemnov.salon.repository.MasterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller

public class MasterController {

    //тут должен быть MasterService, а в нем Repo
    @Autowired
    private MasterRepo masterRepo;

    //    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("/masters")
    public String main(Map<String, Object> model) {
        List<Master> masters = masterRepo.findAll();

        model.put("masters", masters);
        return "masters";
    }

    @PostMapping("/masters")
    public String add(
            @AuthenticationPrincipal User user,
//            @RequestBody User user(или ModelAttribute или как то с Model) попробуй использовать вместо RequestParam
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam Integer rang, Map<String, Object> model){
        Master master = new Master(name, surname, rang);
        masterRepo.save(master);
//        Iterable<Master> masters = masterRepo.findAll();
//        model.put("masters", masters);

        return "redirect:/masters";
    }

    // это должен быть get запрос
    @PostMapping("filter")
    public String filter(@RequestParam Integer rang, Map<String, Object> model){
        Iterable<Master> masters;

        if(rang != 1) {
            masters = masterRepo.findByRang(rang);
        } else masters = masterRepo.findAll();

        model.put("masters", masters);
        return "masters";
    }
}
