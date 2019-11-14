package com.sfg.petclinic.data.service.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.sfg.petclinic.data.model.PetType;
import com.sfg.petclinic.data.service.PetTypeService;

@Service
@Profile(value = {"default","map"})
public class PetTypeServiceMap extends AbstractMapService<PetType, Long> implements PetTypeService {

    @Override
    public PetType findByName(String name) {
        return null;
    }
}
