package com.sfg.petclinic.web.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sfg.petclinic.data.model.Vet;
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
    
    @GetMapping("/api/vets")
    public @ResponseBody Set<Vet> getVetsJson() {
        Set<Vet> vets = vetService.findAll();
        return vets;
    }
}
