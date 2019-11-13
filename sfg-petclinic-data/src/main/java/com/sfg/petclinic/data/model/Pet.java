package com.sfg.petclinic.data.model;

import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="pets")
@AttributeOverride(name = "id", column = @Column(name = "pet_id"))
@SequenceGenerator(name="default_seq", sequenceName="pet_seq", allocationSize=1)
public class Pet extends NamedEntity {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "birth_date")
    private LocalDate birthDate;
    
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    
    @ManyToOne
    @JoinColumn(name = "pet_type_id")
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
