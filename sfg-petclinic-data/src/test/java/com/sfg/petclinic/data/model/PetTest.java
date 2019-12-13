package com.sfg.petclinic.data.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PetTest {

    private static final String PET_NAME = "Chucky";
    
    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void testPetName() {
        Pet pet = new Pet();
        pet.setName(PET_NAME);
        
        assertEquals(PET_NAME, pet.getName());
    }

    @Test
    public void testPetNameBuilder() {
        Pet pet = Pet.builder()
                .name(PET_NAME)
                .build();
        assertEquals(PET_NAME, pet.getName());
    }

    @Test
    public void testPetBirthDate() {
        LocalDate date = LocalDate.now();
        
        Pet pet = Pet.builder()
                .name(PET_NAME)
                .birthDate(date)
                .build();
        
        assertEquals(PET_NAME, pet.getName());
        assertEquals(date, pet.getBirthDate());
    }
}
