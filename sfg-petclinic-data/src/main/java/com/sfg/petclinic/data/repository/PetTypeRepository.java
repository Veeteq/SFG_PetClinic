package com.sfg.petclinic.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.sfg.petclinic.data.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long>{

    PetType findByName(String name);

}
