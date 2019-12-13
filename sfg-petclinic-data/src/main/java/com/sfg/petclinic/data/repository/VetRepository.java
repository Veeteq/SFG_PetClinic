package com.sfg.petclinic.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.sfg.petclinic.data.model.Vet;

public interface VetRepository extends CrudRepository<Vet, Long>{

    Vet findByLastName(String lastName);

}
