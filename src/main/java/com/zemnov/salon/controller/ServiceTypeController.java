package com.zemnov.salon.controller;

import com.zemnov.salon.model.ServiceType;
import com.zemnov.salon.repository.ServiceTypeRepo;
import com.zemnov.salon.service.ServiceTypeService;
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
    private ServiceTypeService serviceTypeService;

    @GetMapping
    public String findAll(@RequestParam(required = false, defaultValue = "1") Integer rang, Model model) {
        List<ServiceType> serviceTypes = serviceTypeService.findAllServiceTypes(rang);

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
            @RequestParam Integer rang)
    {
        serviceType.setName(name);
        serviceType.setPrice(price);
        serviceType.setServiceGroup(serviceGroup);
        serviceType.setDescription(description);
        serviceType.setRang(rang);

        serviceTypeService.saveServiceType(serviceType);

        return ("redirect:/service-types");
    }

    @PostMapping("/delete")
    public String serviceTypeDelete(
            @RequestParam("serviceTypeId") ServiceType serviceType) {
        serviceTypeService.deleteServiceTypeById(serviceType);

        return ("redirect:/service-types");
    }

    @PostMapping
    public String add(
            @RequestParam String name,
            @RequestParam Integer price,
            @RequestParam String serviceGroup,
            @RequestParam String description,
            @RequestParam Integer rang){
        ServiceType serviceType = new ServiceType(name, price,serviceGroup,description, rang);
        serviceTypeService.saveServiceType(serviceType);
        return "redirect:/service-types";
    }

}
