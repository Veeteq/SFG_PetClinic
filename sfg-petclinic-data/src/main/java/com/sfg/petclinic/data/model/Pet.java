package com.sfg.petclinic.data.model;

import java.time.LocalDate;

public class Pet extends NamedEntity {

    private static final long serialVersionUID = 1L;
    
    private LocalDate birthDate;
    private Owner owner;
    private PetType petType;

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }
}
