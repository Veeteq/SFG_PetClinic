package com.sfg.petclinic.data.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="owners")
@AttributeOverride(name = "id", column = @Column(name = "owner_id"))
@SequenceGenerator(name="default_seq", sequenceName="owner_seq", allocationSize=1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Owner extends Person {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private Owner(String firstName, String lastName) {
        super(firstName, lastName);
    }
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "phone")
    private String phone;
    
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    public void addPet(Pet pet) {
        pet.setOwner(this);
        this.pets.add(pet);
    }
    
    public Pet getPet(String name) {
        return getPet(name, false);
    }
    
    public Pet getPet(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for(Pet pet : pets) {
            if(!ignoreNew || !pet.isNew()) {
                if(pet.getName().toLowerCase().equals(name)) {
                    return pet;
                }
            }
        }
        return null;
    }    
}
