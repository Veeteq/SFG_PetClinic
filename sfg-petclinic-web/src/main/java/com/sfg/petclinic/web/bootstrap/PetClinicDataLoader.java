package com.sfg.petclinic.web.bootstrap;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sfg.petclinic.data.model.Owner;
import com.sfg.petclinic.data.model.Pet;
import com.sfg.petclinic.data.model.PetType;
import com.sfg.petclinic.data.model.Speciality;
import com.sfg.petclinic.data.model.Vet;
import com.sfg.petclinic.data.model.Visit;
import com.sfg.petclinic.data.service.OwnerService;
import com.sfg.petclinic.data.service.PetTypeService;
import com.sfg.petclinic.data.service.SpecialityService;
import com.sfg.petclinic.data.service.VetService;
import com.sfg.petclinic.data.service.VisitService;

@Component
public class PetClinicDataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    @Autowired
    public PetClinicDataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.visitService = visitService;
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if(count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        dog = petTypeService.save(dog);
        
        PetType cat = new PetType();
        cat.setName("Cat");
        cat = petTypeService.save(cat);
        
        System.out.println("Pet types loaded...");
        
        Speciality radiology = new Speciality();
        radiology.setName("Radiology");
        radiology = specialityService.save(radiology);
        
        Speciality surgery = new Speciality();
        surgery.setName("Surgery");
        surgery = specialityService.save(surgery);
        
        Speciality dentistry = new Speciality();
        dentistry.setName("Dentistry");
        dentistry = specialityService.save(dentistry);
        
        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Jackson");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setPhone("1231231234");
        
        Pet michaelsPet = new Pet();
        michaelsPet.setName("Rosco");
        michaelsPet.setOwner(owner1);
        michaelsPet.setBirthDate(LocalDate.now());
        michaelsPet.setPetType(dog);
        owner1.getPets().add(michaelsPet);
        ownerService.save(owner1);
        
        Owner owner2 = new Owner();
        owner2.setFirstName("Elton");
        owner2.setLastName("John");
        owner2.setAddress("55 Sunser Boulevard");
        owner2.setCity("Los Angeles");
        owner2.setPhone("55145145145");
        
        Pet eltonsPet = new Pet();
        eltonsPet.setName("Clark");
        eltonsPet.setOwner(owner2);
        eltonsPet.setBirthDate(LocalDate.now());
        eltonsPet.setPetType(dog);
        owner2.getPets().add(eltonsPet);
        ownerService.save(owner2);
        
        Owner owner3 = new Owner();
        owner3.setFirstName("Tina");
        owner3.setLastName("Turner");
        ownerService.save(owner3);

        System.out.println("Owners loaded...");
        
        Vet vet1 = new Vet();
        vet1.setFirstName("Bruce");
        vet1.setLastName("Willis");
        vet1.getSpecialities().add(radiology);
        vetService.save(vet1);
        
        Vet vet2 = new Vet();
        vet2.setFirstName("Angelina");
        vet2.setLastName("Jolie");
        vet2.getSpecialities().add(surgery);
        vetService.save(vet2);
        
        System.out.println("Vets loaded...");
        
        Visit michaelsPetVisit = new Visit();
        michaelsPetVisit.setPet(michaelsPet);
        michaelsPetVisit.setDescription("Broken leg");
        michaelsPetVisit.setDate(LocalDate.now());
        visitService.save(michaelsPetVisit);
        
        System.out.println("Visit loaded...");
    }
}
