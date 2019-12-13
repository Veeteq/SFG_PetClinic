package com.sfg.petclinic.data.service;

import java.util.Set;

import com.sfg.petclinic.data.model.BaseEntity;

public interface CrudService<T extends BaseEntity, ID extends Number> {

    Set<T> findAll();
    T findById(ID id);
    T save(T object);    
    void delete(T object);
    void deleteById(ID id);
}
