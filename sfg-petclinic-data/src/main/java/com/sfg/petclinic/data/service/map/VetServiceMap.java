package com.sfg.petclinic.data.service.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.sfg.petclinic.data.model.Vet;
import com.sfg.petclinic.data.service.SpecialityService;
import com.sfg.petclinic.data.service.VetService;

@Service
@Profile(value = {"default","map"})
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialityService specialityService;
    
    @Autowired
    public VetServiceMap(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Vet save(Vet vet) {
        vet.getSpecialities().forEach(speciality -> {
            if(speciality.getId() == null) {
                speciality.setId(specialityService.save(speciality).getId());
            }
        });
        return super.save(vet);
    }

    @Override
    public Vet findByLastName(String lastName) {
        return null;
    }
}
