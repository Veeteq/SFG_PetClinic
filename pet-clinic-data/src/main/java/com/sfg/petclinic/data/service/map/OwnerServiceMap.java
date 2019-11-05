package com.sfg.petclinic.data.service.map;

import java.util.Set;

import com.sfg.petclinic.data.model.Category;
import com.sfg.petclinic.data.service.CrudService;

public class OwnerServiceMap extends AbstractMapService<Category, Long> implements CrudService<Category, Long> {

    @Override
    public Set<Category> findAll() {
        return super.findAll();
    }

    @Override
    public Category findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Category save(Long id, Category category) {
        return super.save(id, category);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Category category) {
        super.delete(category);
    }

    @Override
    public Category save(Category category) {
        return super.save(category.getId(), category);
    }
}
