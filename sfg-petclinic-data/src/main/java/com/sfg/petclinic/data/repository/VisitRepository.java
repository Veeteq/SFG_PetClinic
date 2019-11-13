package com.sfg.petclinic.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.sfg.petclinic.data.model.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long>{

}
