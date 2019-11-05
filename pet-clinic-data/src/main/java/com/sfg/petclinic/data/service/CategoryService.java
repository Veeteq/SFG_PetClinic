package com.sfg.petclinic.data.service;

import java.util.List;

import com.sfg.petclinic.data.model.Category;

public interface CategoryService {

    List<Category> findAll();
    Category findById(Long id);
    Category save(Category pet);
    Category findByLastName(String name);
}
