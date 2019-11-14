package com.sfg.petclinic.data.service;

import com.sfg.petclinic.data.model.Item;

public interface ItemService extends CrudService<Item, Long>{

    Item findByName(String name);
}
