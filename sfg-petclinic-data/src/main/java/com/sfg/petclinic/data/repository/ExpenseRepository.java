package com.sfg.petclinic.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.sfg.petclinic.data.model.Expense;

public interface ExpenseRepository extends CrudRepository<Expense, Long>{

}
