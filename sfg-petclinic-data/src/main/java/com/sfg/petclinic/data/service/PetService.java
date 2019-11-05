package com.sfg.petclinic.data.service;

import com.sfg.petclinic.data.model.Pet;

public interface PetService extends CrudService<Pet, Long>{

    Pet findByName(String name);
}
