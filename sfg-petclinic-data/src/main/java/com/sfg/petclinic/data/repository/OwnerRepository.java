package com.sfg.petclinic.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.sfg.petclinic.data.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long>{

    Owner findByLastName(String lastName);

}
