package com.sfg.petclinic.data.service;

import java.util.List;

import com.sfg.petclinic.data.model.Pet;

public interface PetService {

    List<Pet> findAll();
    Pet findById(Long id);
    Pet save(Pet pet);
    Pet findByName(String name);
}
