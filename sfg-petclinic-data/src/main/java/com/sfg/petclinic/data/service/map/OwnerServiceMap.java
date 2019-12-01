package com.sfg.petclinic.data.service.map;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.sfg.petclinic.data.model.Owner;
import com.sfg.petclinic.data.service.OwnerService;
import com.sfg.petclinic.data.service.PetService;
import com.sfg.petclinic.data.service.PetTypeService;

@Service
@Profile(value = {"default","map"})
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private PetTypeService petTypeService;
    private PetService petService;
    
    @Autowired
    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Owner save(Owner owner) {
        if(owner != null) {
            if(owner.getPets() != null) {
                owner.getPets().forEach(pet -> {
                    if(pet.getPetType() != null) {
                        if(pet.getPetType().getId() == null) {
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    } else {
                        throw new RuntimeException("PetType is required!");
                    }
                    
                    if(pet.getId() == null) {
                        pet.setId(petService.save(pet).getId());
                    }
                });
            }
            return super.save(owner);
        } else {
            return null;
        }
    }

    @Override
    public Owner findByLastName(String lastName) {
        return map.values()
                .stream()
                .filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Set<Owner> findAllByLastNameLike(String lastName) {
        return map.values()
                .stream()
                .filter(owner -> owner.getLastName().contains(lastName)).collect(Collectors.toSet());
        }
}
