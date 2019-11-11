package com.sfg.petclinic.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sfg.petclinic.data.service.OwnerService;

@Controller
@RequestMapping(path = "/owners")
public class OwnerController {

    private final OwnerService ownerService;
    
    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping(path = {"", "/", "/index", "/index.html"}, method = RequestMethod.GET)
    public String getOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index"; 
    }
    
    @RequestMapping(path = {"/find"})
    public String findOwners() {
        return "notimplemented";
    }
}
