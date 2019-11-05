package com.sfg.petclinic.data.service;

import com.sfg.petclinic.data.model.Vet;

public interface VetService extends CrudService<Vet, Long>{

    Vet findByLastName(String lastName);
}
