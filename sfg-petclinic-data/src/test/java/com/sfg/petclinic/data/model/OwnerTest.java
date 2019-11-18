package com.sfg.petclinic.data.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OwnerTest {

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void testPersonNameSetter() {
        Owner owner1 = new Owner();
        owner1.setFirstName("Bruce");
        
        assertEquals("Bruce", owner1.getFirstName());
    }

    @Test
    public void testPersonNameBuilder() {
        Owner owner2 = Owner.builder()
                .firstName("Bruce")
                .build();
        
        assertEquals("Bruce", owner2.getFirstName());
    }
    
    @Test
    public void testOwnerPets() {
        Owner owner = Owner.builder()
                .firstName("Bruce")
                .build();
        
        assertNotNull(owner.getPets());
    }
    
    @Test
    public void testOwnerAddPet() {
        Pet pet = Pet.builder().name("Lucky").build();
        
        Owner owner = Owner.builder()
                .firstName("Bruce")               
                .build();
        owner.addPet(pet);
        
        assertNotNull(owner.getPets());
        assertEquals(1, owner.getPets().size());
        assertEquals("Lucky", owner.getPets().iterator().next().getName());
    }
}
