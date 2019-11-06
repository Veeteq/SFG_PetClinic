package com.sfg.petclinic.web.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sfg.petclinic.data.model.Owner;
import com.sfg.petclinic.data.model.Vet;
import com.sfg.petclinic.data.service.OwnerService;
import com.sfg.petclinic.data.service.VetService;

@Component
public class PetClinicDataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    @Autowired
    public PetClinicDataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Jackson");
        ownerService.save(owner1);
        
        Owner owner2 = new Owner();
        owner2.setFirstName("Elton");
        owner2.setLastName("John");
        ownerService.save(owner2);
        
        Owner owner3 = new Owner();
        owner3.setFirstName("Tina");
        owner3.setLastName("Turner");
        ownerService.save(owner3);

        System.out.println("Owners loaded...");
        
        Vet vet1 = new Vet();
        vet1.setFirstName("Bruce");
        vet1.setLastName("Willis");
        vetService.save(vet1);
        
        Vet vet2 = new Vet();
        vet2.setFirstName("Angelina");
        vet2.setLastName("Jolie");
        vetService.save(vet2);
        
        System.out.println("Vets loaded...");
    }
}
