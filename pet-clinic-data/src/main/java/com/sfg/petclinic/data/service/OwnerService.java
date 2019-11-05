package com.sfg.petclinic.data.service;

import java.util.List;

import com.sfg.petclinic.data.model.Owner;

public interface OwnerService {

    List<Owner> findAll();
    Owner findById(Long id);
    Owner save(Owner owner);
    Owner findByLastName(String lastName);
}
