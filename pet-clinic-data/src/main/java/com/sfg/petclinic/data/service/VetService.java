package com.sfg.petclinic.data.service;

import java.util.List;

import com.sfg.petclinic.data.model.Vet;

public interface VetService {

    List<Vet> findAll();
    Vet findById(Long id);
    Vet save(Vet vet);
    Vet findByLastName(String lastName);
}
