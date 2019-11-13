package com.sfg.petclinic.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.sfg.petclinic.data.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long>{

}
