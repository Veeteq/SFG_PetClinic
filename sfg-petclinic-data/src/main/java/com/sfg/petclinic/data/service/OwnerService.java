package com.sfg.petclinic.data.service;

import java.util.Set;

import com.sfg.petclinic.data.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{

    Owner findByLastName(String lastName);

    Set<Owner> findAllByLastNameLike(String lastName);
}
