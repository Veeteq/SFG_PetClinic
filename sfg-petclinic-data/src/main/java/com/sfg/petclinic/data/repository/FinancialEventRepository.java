package com.sfg.petclinic.data.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.sfg.petclinic.data.model.FinancialEvent;

public interface FinancialEventRepository extends CrudRepository<FinancialEvent, Long>{

    Set<FinancialEvent> findAllByNameLike(String name);

}
