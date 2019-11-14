package com.sfg.petclinic.data.service.map;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.sfg.petclinic.data.model.Item;
import com.sfg.petclinic.data.service.ItemService;

@Service
@Profile(value = {"default","map"})
public class ItemServiceMap extends AbstractMapService<Item, Long> implements ItemService {

    @Override
    public Set<Item> findAll() {
        return super.findAll();
    }

    @Override
    public Item findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Item save(Item item) {
        return super.save(item);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Item item) {
        super.delete(item);
    }

    @Override
    public Item findByName(String name) {
        return null;
    }
}
