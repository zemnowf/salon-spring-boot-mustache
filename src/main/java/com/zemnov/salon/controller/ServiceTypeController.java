package com.zemnov.salon.controller;

import com.zemnov.salon.model.ServiceType;
import com.zemnov.salon.repository.ServiceTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/service-types")
public class ServiceTypeController {

    @Autowired
    private ServiceTypeRepo serviceTypeRepo;

    @GetMapping
    public String findAll(@RequestParam(required = false, defaultValue = "1") Integer rang, Model model) {
        List<ServiceType> serviceTypes = serviceTypeRepo.findAll();

        if(rang != 1) {
            serviceTypes = serviceTypeRepo.findByRang(rang);
        } else serviceTypes = serviceTypeRepo.findAll();

        model.addAttribute("serviceTypes", serviceTypes);
        return "service-types";
    }

    @GetMapping("{serviceType}/edit")
    public String serviceTypeEditForm(@PathVariable ServiceType serviceType, Model model) {
        model.addAttribute("serviceType", serviceType);

        return "serviceTypeEdit";
    }

    @GetMapping("{serviceType}/delete")
    public String serviceTypeDeleteForm(@PathVariable ServiceType serviceType, Model model) {
        model.addAttribute("serviceType", serviceType);

        return "serviceTypeDelete";
    }

    @PostMapping("/edit")
    public String serviceTypeSave(
            @RequestParam("serviceTypeId") ServiceType serviceType,
            @RequestParam String name,
            @RequestParam Integer price,
            @RequestParam String serviceGroup,
            @RequestParam String description,
            @RequestParam Integer rang, Map<String, Object> model)
    {
        serviceType.setName(name);
        serviceType.setPrice(price);
        serviceType.setServiceGroup(serviceGroup);
        serviceType.setDescription(description);
        serviceType.setRang(rang);

        serviceTypeRepo.save(serviceType);

        return ("redirect:/service-types");
    }

    @PostMapping("/delete")
    public String serviceTypeDelete(
            @RequestParam("serviceTypeId") ServiceType serviceType) {
        serviceTypeRepo.deleteById(serviceType.getId());

        return ("redirect:/service-types");
    }

    @PostMapping
    public String add(
            @RequestParam String name,
            @RequestParam Integer price,
            @RequestParam String serviceGroup,
            @RequestParam String description,
            @RequestParam Integer rang, Map<String, Object> model){
        ServiceType serviceType = new ServiceType(name, price,serviceGroup,description, rang);
        serviceTypeRepo.save(serviceType);
        return "redirect:/service-types";
    }

}
