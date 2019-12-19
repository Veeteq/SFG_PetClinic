package com.sfg.petclinic.data.service;

import java.util.Set;

import com.sfg.petclinic.data.model.FinancialEvent;

public interface FinancialEventService extends CrudService<FinancialEvent, Long>{

    Set<FinancialEvent> findAllByNameLike(String name);

}
