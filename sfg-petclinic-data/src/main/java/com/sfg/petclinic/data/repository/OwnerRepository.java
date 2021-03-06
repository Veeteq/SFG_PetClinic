package com.sfg.petclinic.data.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.sfg.petclinic.data.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long>{

    Owner findByLastName(String lastName);

    Set<Owner> findAllByLastNameLike(String lastName);

}
