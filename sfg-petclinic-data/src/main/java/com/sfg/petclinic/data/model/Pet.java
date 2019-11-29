package com.sfg.petclinic.data.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @Column(name = "birth_date")
    private LocalDate birthDate;
    
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    
    @ManyToOne
    @JoinColumn(name = "pet_type_id")
    private PetType petType;
    
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();
}
