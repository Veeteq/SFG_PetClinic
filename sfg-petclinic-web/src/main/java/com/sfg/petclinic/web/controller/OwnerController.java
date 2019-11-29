package com.sfg.petclinic.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sfg.petclinic.data.service.OwnerService;

@Controller
@RequestMapping(path = "/owners")
public class OwnerController {

    private final OwnerService ownerService;
    
    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder datBinder) {
        datBinder.setDisallowedFields("id");
    }
    
    @GetMapping(path = {"", "/", "/index", "/index.html"})
    public String getOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index"; 
    }
    
    @GetMapping(path = {"/find"})
    public String findOwners() {
        return "notimplemented";
    }
    
    @GetMapping(path = {"/{ownerId}"})
    public ModelAndView showOwners(@PathVariable(name = "ownerId") Long ownerId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("owners/ownerDetails");
        modelAndView.addObject(ownerService.findById(ownerId));
        
        return modelAndView;
    }
}
