package com.sfg.petclinic.data.service.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.sfg.petclinic.data.model.BaseEntity;
import com.sfg.petclinic.data.service.CrudService;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Number> implements CrudService<T, ID>{
    
    protected Map<Long, T> map = new HashMap<>();

    public Set<T> findAll() {
        return new HashSet<T>(map.values());
    }
    
    public T findById(ID id) {
        return map.get(id);
    }
    
    public T save(T object) {
        if(object != null) {
            if(object.getId() == null) {
                object.setId(getNextId());
            }
            map.put(object.getId(), object);
        } else {
            throw new RuntimeException("Object cannot be null");
        }

        return object;
    }
    
    public void deleteById(ID id) {
        map.remove(id);
    }
    
    public void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }
    
    public Long getNextId() {
        if(map.isEmpty()) {
            return 1L;
        }
        
        return Collections.max(map.keySet()) + 1;
    }
}
