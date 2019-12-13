package com.sfg.petclinic.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sfg.petclinic.data.service.VetService;

@Controller
public class VetController {

    private final VetService vetService;
    
    @Autowired
    public VetController(VetService vetService) {
        this.vetService = vetService;
    }
    
    @RequestMapping(path = {"/vets", "/vets/", "/vets/index", "/vets/index.html", "vets.html"}, method = RequestMethod.GET)
    public String getVets(Model model) {
        model.addAttribute("vets", vetService.findAll());
        return "vets/index"; 
    }
}
