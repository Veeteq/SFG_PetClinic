package com.sfg.petclinic.data.service;

import com.sfg.petclinic.data.model.Category;

public interface CategoryService extends CrudService<Category, Long>{

    Category findByName(String name);
}
