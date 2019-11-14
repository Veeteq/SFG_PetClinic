package com.sfg.petclinic.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.sfg.petclinic.data.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long>{

    Item findByName(String name);

}
