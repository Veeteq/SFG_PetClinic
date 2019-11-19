package com.sfg.petclinic.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.sfg.petclinic.data.model.Income;

public interface IncomeRepository extends CrudRepository<Income, Long> {

}
