package com.sfg.petclinic.web.controller;

import java.util.Collection;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfg.petclinic.data.model.Owner;
import com.sfg.petclinic.data.model.Pet;
import com.sfg.petclinic.data.model.PetType;
import com.sfg.petclinic.data.service.OwnerService;
import com.sfg.petclinic.data.service.PetService;
import com.sfg.petclinic.data.service.PetTypeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(path = "owners/{ownerId}")
@Slf4j
public class PetController {
    private static final String VIEWS_PET_ADD_OR_UPDATE = "pets/addOrUpdate";

    private final PetTypeService petTypeService;
    private final OwnerService ownerService;
    private final PetService petService;
    
    @Autowired
    public PetController(PetTypeService petTypeService, OwnerService ownerService, PetService petService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
    }

    @ModelAttribute(name = "petTypes")
    public Collection<PetType> getPetTypes() {
        log.debug("PetController: getPetTypes");
        return petTypeService.findAll();
    }
    
    @ModelAttribute(name = "owner")
    public Owner findOwnerById(@PathVariable(name = "ownerId") Long ownerId) {
        log.debug("PetController: findOwnerById: " + ownerId);
        return ownerService.findById(ownerId);
    }
    
    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
    
    @GetMapping(path = "/pets/new")
    public String newPetFormShow(Owner owner, Model model) {
        log.debug("PetController: newPetFormShow");
        Pet pet = Pet.builder().build();
        owner.addPet(pet);
        model.addAttribute("pet", pet);
        return VIEWS_PET_ADD_OR_UPDATE;
    }

    @PostMapping(path = "/pets/new")
    public String newPetFormPost(Owner owner, @Valid Pet pet, BindingResult result, Model model) {
        log.debug("PetController: newPetFormShowPost");
        
        if(StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
            log.debug("Record rejected");
            result.rejectValue("name", "duplicate", "already exists");
        }
        
        owner.addPet(pet);
        
        if(result.hasErrors()) {
            log.debug("Record has errors");
            model.addAttribute("pet", pet);
            return VIEWS_PET_ADD_OR_UPDATE;
        } else {
            log.debug("Record saved");
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }
    
    @GetMapping(path = "/pets/{petId}/edit")
    public String editPetFormShow(@PathVariable(name = "petId") Long petId, Model model) {
        log.debug("PetController: editPetFormShow");
        model.addAttribute("pet", petService.findById(petId));
        return VIEWS_PET_ADD_OR_UPDATE;
    }
    
    @PostMapping(path = "/pets/{petId}/edit")
    public String editPetFormPost(Owner owner, @Valid Pet pet, BindingResult result, Model model) {
        log.debug("PetController: editPetFormPost");

        if(result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEWS_PET_ADD_OR_UPDATE;
        } else {
            log.debug("Record saved");
            owner.addPet(pet);
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }    
}
