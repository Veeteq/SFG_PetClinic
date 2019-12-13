package com.sfg.petclinic.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.sfg.petclinic.data.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{

	Category findByName(String name);
}
