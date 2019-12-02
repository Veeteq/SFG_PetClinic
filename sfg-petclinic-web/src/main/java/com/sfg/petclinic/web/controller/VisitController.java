package com.sfg.petclinic.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfg.petclinic.data.model.Pet;
import com.sfg.petclinic.data.model.Visit;
import com.sfg.petclinic.data.service.PetService;
import com.sfg.petclinic.data.service.VisitService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(path = "owners/{ownerId}/pets/{petId}")
@Slf4j
public class VisitController {
    
    private static final String VIEWS_VISIT_ADD_OR_UPDATE = "visits/addOrUpdate";
    
    private final VisitService visitService;
    private final PetService petService;
    
    @Autowired
    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder("visit")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute
    public Visit createVisitForPet(@PathVariable(name = "petId") Long petId, Model model) {
        log.debug("VisitController: createVisitForPet");
        Pet pet = petService.findById(petId);
        Visit visit = Visit.builder().build();
        pet.addVisit(visit);
        model.addAttribute("pet", pet);
        return visit;
    }
    
    @GetMapping(path = "/visits/new")
    public String newVisitFormShow(@PathVariable(name = "petId") Long petId, Model model) {
        log.debug("VisitController: newVisitFormShow");
        return VIEWS_VISIT_ADD_OR_UPDATE;
    }

    @PostMapping(path = "/visits/new")
    public String newVisitFormPost(@Valid Visit visit, BindingResult result, Model model) {
        log.debug("PetController: newPetFormShowPost");
        
        if(result.hasErrors()) {
            log.debug("Record has errors");
            return VIEWS_VISIT_ADD_OR_UPDATE;
        } else {
            log.debug("Record saved");
            visitService.save(visit);
            return "redirect:/owners/1";// + owner.getId();
        }
    }
}
