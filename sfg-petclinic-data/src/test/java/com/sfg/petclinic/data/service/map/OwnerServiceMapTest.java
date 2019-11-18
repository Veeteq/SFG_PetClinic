package com.sfg.petclinic.data.service.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sfg.petclinic.data.model.Owner;

class OwnerServiceMapTest {

    private OwnerServiceMap ownerServiceMap;
    private Long ownerId = 1L;
    private String lastName = "Smith";
    
    @BeforeEach
    void setUp() throws Exception {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
        
        Owner owner = Owner.builder().id(ownerId).lastName(lastName).build();
        
        ownerServiceMap.save(owner);
    }

    @Test
    void testSaveOwnerWithId() {
        Long longId = 2L;
        Owner owner = Owner.builder().id(longId).build();
        
        Owner savedOwner = ownerServiceMap.save(owner);
        assertEquals(longId, savedOwner.getId());
    }

    @Test
    void testFindByLastName() {
        Owner owner = ownerServiceMap.findByLastName(lastName);
        assertNotNull(owner);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void testFindByLastNameNotFound() {
        Owner owner = ownerServiceMap.findByLastName("bla");
        assertNull(owner);
    }

    @Test
    void testFindAll() {
        Set<Owner> all = ownerServiceMap.findAll();
        assertEquals(1, all.size());
    }

    @Test
    void testFindById() {
        Owner owner = ownerServiceMap.findById(ownerId);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void testSaveOwnerWithoutId() {
        Owner owner = Owner.builder().build();
        
        Owner savedOwner = ownerServiceMap.save(owner);
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void testDeleteById() {
        ownerServiceMap.deleteById(ownerId);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void testDelete() {
        Owner owner = ownerServiceMap.findById(ownerId);
        ownerServiceMap.delete(owner);
        assertEquals(0, ownerServiceMap.findAll().size());
    }
}
