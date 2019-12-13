package com.sfg.petclinic.data.service;

import com.sfg.petclinic.data.model.PetType;

public interface PetTypeService extends CrudService<PetType, Long>{

    PetType findByName(String name);
}
