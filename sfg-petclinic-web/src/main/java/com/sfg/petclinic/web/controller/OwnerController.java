package com.sfg.petclinic.web.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sfg.petclinic.data.model.Owner;
import com.sfg.petclinic.data.service.OwnerService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
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
/*    
    @GetMapping(path = {"", "/", "/index", "/index.html"})
    public String getOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index"; 
    }
*/    
    @GetMapping(path = {"/find"})
    public String findOwnersFormShow(Model model) {
        log.debug("OwnerController: findOwnersFormShow");
        
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }
    
    @GetMapping(path = {"", "/"})
    public String findOwnersFormSend(Owner owner, BindingResult result, Model model) {
        log.debug("OwnerController: findOwnersFormSend");
        if(owner.getLastName() == null) {
            owner.setLastName("");
        }
        
        String lastName = owner.getLastName();
        Set<Owner> owners = ownerService.findAllByLastNameLike(lastName);
        if(owners.isEmpty()) {
            log.debug("OwnerController: owners.isEmpty()");
            result.rejectValue("lastName", "notFound", "Not Found!");
            return "owners/findOwners";
        } else if (owners.size() == 1) {
            log.debug("OwnerController: owners.size() == 1");
            owner = owners.iterator().next();
            return "redirect:/owners/" + owner.getId();
        } else {
            log.debug("OwnerController: owners > 1");
            model.addAttribute("owners", owners);
            return "owners/ownersList";
        }
    }
    
    @GetMapping(path = {"/{ownerId}"})
    public ModelAndView showOwners(@PathVariable(name = "ownerId") Long ownerId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("owners/ownerDetails");
        modelAndView.addObject(ownerService.findById(ownerId));
        
        return modelAndView;
    }
}
