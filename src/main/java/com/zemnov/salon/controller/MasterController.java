package com.zemnov.salon.controller;

import com.zemnov.salon.model.Master;
import com.zemnov.salon.model.User;
import com.zemnov.salon.repository.MasterRepo;
import com.zemnov.salon.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/masters")
public class MasterController {

    //тут должен быть MasterService, а в нем Repo
    @Autowired
    private MasterRepo masterRepo;
    private MasterService masterService;

//    @GetMapping("/masters")
//    public String main()

    //    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping
    public String main(@RequestParam(required = false, defaultValue = "1") Integer rang,  Model model) {
        List<Master> masters = masterRepo.findAll();

        if(rang != 1) {
            masters = masterRepo.findByRang(rang);
        } else masters = masterRepo.findAll();

        model.addAttribute("masters", masters);

        return "masters";
    }

    @GetMapping("{master}/edit")
    public String masterEditForm(@PathVariable Master master, Model model) {
        model.addAttribute("master", master);

        return "masterEdit";
    }

    @GetMapping("{master}/delete")
    public String userDeleteForm(@PathVariable Master master, Model model) {
        model.addAttribute("master", master);

        return "masterDelete";
    }

    @PostMapping("/edit")
    public String masterSave(
            @RequestParam("masterId") Master master,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam Integer rang, Map<String, Object> model)
    {
        master.setName(name);
        master.setSurname(surname);
        master.setRang(rang);

        masterRepo.save(master);

        return ("redirect:/masters");
    }

    @PostMapping("/delete")
    public String masterDelete(
            @RequestParam("masterId") Master master
            )
    {
        masterRepo.deleteById(master.getId());
        return ("redirect:/masters");
    }


    @PostMapping
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

}
