package com.sfg.petclinic.web.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sfg.petclinic.data.model.Owner;
import com.sfg.petclinic.data.model.Vet;
import com.sfg.petclinic.data.service.OwnerService;
import com.sfg.petclinic.data.service.VetService;
import com.sfg.petclinic.data.service.map.OwnerServiceMap;
import com.sfg.petclinic.data.service.map.VetServiceMap;

@Component
public class PetClinicDataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    
    public PetClinicDataLoader() {
        this.ownerService = new OwnerServiceMap();
        this.vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Michael");
        owner1.setLastName("Jackson");
        ownerService.save(owner1);
        
        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Elton");
        owner2.setLastName("John");
        ownerService.save(owner2);
        
        Owner owner3 = new Owner();
        owner3.setId(3L);
        owner3.setFirstName("Tina");
        owner3.setLastName("Turner");
        ownerService.save(owner3);

        System.out.println("Owners loaded...");
        
        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Bruce");
        vet1.setLastName("Willis");
        vetService.save(vet1);
        
        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Angelina");
        vet2.setLastName("Jolie");
        vetService.save(vet2);
        
        System.out.println("Vets loaded...");
    }
}
