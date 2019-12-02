package com.sfg.petclinic.web.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfg.petclinic.data.model.Owner;
import com.sfg.petclinic.data.model.PetType;
import com.sfg.petclinic.data.service.OwnerService;
import com.sfg.petclinic.data.service.PetTypeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(path = "owers/{ownerId}")
@Slf4j
public class PetController {

    private final PetTypeService petTypeService;
    private final OwnerService ownerService;

    @Autowired
    public PetController(PetTypeService petTypeService, OwnerService ownerService) {
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
    }

    @ModelAttribute(name = "petTypes")
    public Collection<PetType> getPetTypes() {
        log.debug("PetController: + getPetTypes");
        return petTypeService.findAll();
    }
    
    @ModelAttribute(name = "owners")
    public Owner findOwnerById(@PathVariable(name = "ownerId") Long ownerId) {
        log.debug("PetController: + findOwnerById: " + ownerId);
        return ownerService.findById(ownerId);
    }
    
    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
}
