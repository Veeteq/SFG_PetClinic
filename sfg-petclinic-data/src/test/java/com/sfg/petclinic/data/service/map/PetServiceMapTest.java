package com.sfg.petclinic.data.service.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sfg.petclinic.data.model.Pet;

class PetServiceMapTest {

    private PetServiceMap petServiceMap;
    private final Long petId = 1L;
    private final String petName = "Taco";
    
    @BeforeEach
    void setUp() throws Exception {
        petServiceMap = new PetServiceMap();
        petServiceMap.save(Pet.builder().id(petId).name(petName).build());
    }

    @Test
    void testFindAll() {
        Set<Pet> pets = petServiceMap.findAll();
        assertEquals(1, pets.size());
    }

    @Test
    void testFindByIdLong() {
        Pet pet = petServiceMap.findById(petId);
        assertEquals(1, pet.getId());
    }

    @Test
    void testFindByIdNull() {
        Pet pet = petServiceMap.findById(null);
        assertNull(pet);
    }
    
    @Test
    void testFindByBadId() {
        Pet pet = petServiceMap.findById(5L);
        assertNull(pet);
    }
    
    @Test
    void testSaveNewPet() {
        Long id = 2L;
        Pet pet = Pet.builder().id(id).build();
        Pet savedPet = petServiceMap.save(pet);
        assertEquals(id, savedPet.getId());
    }

    @Test
    void testSaveExistingPet() {
        Long id = 1L;
        Pet pet = Pet.builder().id(id).build();
        Pet savedPet = petServiceMap.save(pet);
        assertEquals(id, savedPet.getId());
        assertEquals(1, petServiceMap.findAll().size());
    }
    
    @Test
    void testSavePetNoId() {
        Pet pet = Pet.builder().build();
        Pet savedPet = petServiceMap.save(pet);
        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
        assertEquals(2, petServiceMap.findAll().size());
    }
    
    @Test
    void testDeleteByIdLong() {
        petServiceMap.deleteById(petId);
        assertEquals(0, petServiceMap.findAll().size());
    }

    @Test
    void testDeletePet() {
        Pet pet = petServiceMap.findById(petId);
        petServiceMap.delete(pet);
        assertEquals(0, petServiceMap.findAll().size());
    }

    @Test
    void testDeleteByBadId() {
        Long id = 5L;
        petServiceMap.deleteById(id);
        assertEquals(1, petServiceMap.findAll().size());
    }
    
    @Test
    void testDeleteByIdNull() {
        petServiceMap.deleteById(null);
        assertEquals(1, petServiceMap.findAll().size());
    }
    
    @Test
    void testDeleteWithNullId() {
        Pet pet = Pet.builder().build();
        petServiceMap.delete(pet);
        assertEquals(1, petServiceMap.findAll().size());
    }
    
    @Test
    void testDeleteNull() {        
        petServiceMap.delete(null);
        assertEquals(1, petServiceMap.findAll().size());
    }
    
    @Test
    void testFindByName() {
        Pet pet = petServiceMap.findByName(petName);
        assertNotNull(pet);
        assertEquals(1, pet.getId());
        assertEquals(petName, pet.getName());
    }

    @Test
    void testFindByWrongName() {
        Pet pet = petServiceMap.findByName("Coco");
        assertNull(pet);
    }
}
