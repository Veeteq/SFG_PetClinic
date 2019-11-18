package com.sfg.petclinic.data.model;

import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="pets")
@AttributeOverride(name = "id", column = @Column(name = "pet_id"))
@SequenceGenerator(name="default_seq", sequenceName="pet_seq", allocationSize=1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Pet extends NamedEntity {

    private static final long serialVersionUID = 1L;

    public Pet(String name) {
        super(name);
    }
    
    @Column(name = "birth_date")
    private LocalDate birthDate;
    
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    
    @ManyToOne
    @JoinColumn(name = "pet_type_id")
    private PetType petType;
}
