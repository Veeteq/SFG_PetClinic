package com.sfg.petclinic.web.bootstrap;

import java.time.LocalDate;
import java.util.HashSet;

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

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
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
        PetType dog = PetType.builder().name("Dog").build();
        dog = petTypeService.save(dog);
        
        PetType cat = PetType.builder().name("Cat").build();        
        cat = petTypeService.save(cat);
        
        log.debug("Pet types loaded...");
        
        Speciality radiology = Speciality.builder().name("Radiology").build();
        radiology = specialityService.save(radiology);
        
        Speciality surgery = Speciality.builder().name("Surgery").build();
        surgery = specialityService.save(surgery);
        
        Speciality dentistry = Speciality.builder().name("Dentistry").build();
        dentistry = specialityService.save(dentistry);
        
        Owner owner1 = Owner.builder()
        .firstName("Michael")
        .lastName("Jackson")
        .address("123 Brickerel")
        .city("Miami")
        .phone("1231231234")
        .pets(new HashSet<>())
        .build();
        
        Pet michaelsPet = Pet.builder()
        .name("Rosco")
        .owner(owner1)
        .birthDate(LocalDate.now())
        .petType(dog)
        .build();
        owner1.addPet(michaelsPet);        
        ownerService.save(owner1);
        
        Owner owner2 = Owner.builder()
        .firstName("Elton")
        .lastName("John")
        .address("55 Sunser Boulevard")
        .city("Los Angeles")
        .phone("55145145145")
        .pets(new HashSet<>())
        .build();
        
        Pet eltonsPet = Pet.builder()
        .name("Clark")
        .owner(owner2)
        .birthDate(LocalDate.now())
        .petType(dog)
        .build();
        owner2.addPet(eltonsPet);
        ownerService.save(owner2);
        
        Owner owner3 = Owner.builder()
        .firstName("Tina")
        .lastName("Turner")
        .build();
        ownerService.save(owner3);

        Owner owner4 = Owner.builder()
        .firstName("Samuel L.")
        .lastName("Jackson")
        .build();
        ownerService.save(owner4);

        log.debug("Owners loaded...");
        
        Vet vet1 = Vet.builder()        		
        .firstName("Bruce")
        .lastName("Willis")
        .specialities(new HashSet<>())
        .build();
        System.out.println("getFirstName: " + vet1.getFirstName());
        vet1.getSpecialities().add(radiology);
        vet1.getSpecialities().add(dentistry);
        vetService.save(vet1);
        
        Vet vet2 = Vet.builder()
        .firstName("Angelina")
        .lastName("Jolie")
        .specialities(new HashSet<>())
        .build();
        vet2.getSpecialities().add(surgery);
        vetService.save(vet2);

        Vet vet3 = Vet.builder()
                .firstName("Janet")
                .lastName("Jackson")
                .specialities(new HashSet<>())
                .build();                
                vetService.save(vet3);
                
        log.debug("Vets loaded...");
        
        Visit michaelsPetVisit = new Visit();
        michaelsPetVisit.setPet(michaelsPet);
        michaelsPetVisit.setDescription("Broken leg");
        michaelsPetVisit.setVisitDate(LocalDate.now());
        visitService.save(michaelsPetVisit);
        
        log.debug("Visit loaded...");
    }
}
